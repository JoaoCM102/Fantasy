package com.example.trabajo_firebase.firebase

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.trabajo_firebase.firebase.clases.Comentarios
import com.example.trabajo_firebase.firebase.clases.Users
import com.example.trabajo_firebase.modelo.jugadores
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ViewModelComentario : ViewModel() {

    // conexion mysql...
    val conexion = FirebaseFirestore.getInstance()


    // Lista de toda la grifa, que la actualizará Firebase.
    private var _listaGrifa =
        MutableStateFlow(mutableStateListOf<Comentarios>())
    var listaGrifa = _listaGrifa.asStateFlow()
    private lateinit var listenerReg : ListenerRegistration

    fun crearListener(){

        // ponemos la oreja
        conexion.collection("comentario").addSnapshotListener{
                datos, error ->
            if(error == null) {
                // ¿Que cambios nuevos ha habido en la BBDD?
                datos?.documentChanges?.forEach { cambio ->
                    if(cambio.type == DocumentChange.Type.ADDED) {
                        // añadimos elemento a la lista UI
                        var nuevaGrifa =  cambio.document.toObject<Comentarios>()
                        // me guardo el id del documento
                        nuevaGrifa.idComentario = cambio.document.id
                        _listaGrifa.value.add(nuevaGrifa)

                    }
                    else if(cambio.type == DocumentChange.Type.REMOVED) {
                        // borramos elemento de la lista UI
                        var nuevaGrifa =  cambio.document.toObject<Comentarios>()
                        _listaGrifa.value.remove(nuevaGrifa)
                    }
                    else if(cambio.type == DocumentChange.Type.MODIFIED) {
                        // modificamos elemento de la lista UI
                        var nuevaGrifa =  cambio.document.toObject<Comentarios>()
                        cambio.document.id;
                        // buscar el elemento con dicho id, y entonces borrarlo.
                        _listaGrifa.value[cambio.newIndex] = nuevaGrifa
                    }

                }
            }
        }
    }
    fun borrarListener(){
        listenerReg.remove()
    }



    fun borrarDroga(drogaAborrar : Users){
        conexion.collection("users")
            .document(drogaAborrar.userId).delete()
    }

    fun actualizar(drogaCambiar: Users) {
        conexion.collection("users")
            .document(drogaCambiar.userId).update("cantidad",5)
    }

}