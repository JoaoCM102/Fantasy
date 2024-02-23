package com.example.trabajo_firebase.firebase

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.trabajo_firebase.firebase.clases.Noticias
import com.example.trabajo_firebase.firebase.clases.Users
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.toObject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ViewModelFirebaseNoticias : ViewModel() {

    // conexion mysql...
    val conexion = FirebaseFirestore.getInstance()


    // Lista de toda la grifa, que la actualizará Firebase.
    private var _listaGrifa =
        MutableStateFlow(mutableStateListOf<Noticias>())
    var listaGrifa = _listaGrifa.asStateFlow()

    private lateinit var listenerReg : ListenerRegistration

    fun crearListener(){

        _listaGrifa.value.clear()
        // ponemos la oreja
            listenerReg = conexion.collection("noticias").addSnapshotListener{
                    datos, error ->
                if(error == null) {
                    // ¿Que cambios nuevos ha habido en la BBDD?
                    datos?.documentChanges?.forEach { cambio ->
                        if(cambio.type == DocumentChange.Type.ADDED) {
                            // añadimos elemento a la lista UI
                            println(cambio.document.toString())
                            var nuevaGrifa =  cambio.document.toObject<Noticias>()
                            // me guardo el id del documento
                            nuevaGrifa.idNoticia = cambio.document.id
                            _listaGrifa.value.add(nuevaGrifa)

                        }
                        else if(cambio.type == DocumentChange.Type.REMOVED) {
                            // borramos elemento de la lista UI
                            var nuevaGrifa =  cambio.document.toObject<Noticias>()
                            _listaGrifa.value.remove(nuevaGrifa)
                        }
                        else if(cambio.type == DocumentChange.Type.MODIFIED) {
                            // modificamos elemento de la lista UI
                            var nuevaGrifa =  cambio.document.toObject<Noticias>()
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
    fun anyadirNoticia(titulo: String, texto: String,encabezado : String){
        var Noticia = Noticias(null,"",encabezado, Users.valorAutor,texto,titulo)
        conexion.collection("noticias").add(Noticia)
    }



}