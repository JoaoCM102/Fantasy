package com.example.trabajo_firebase.firebase

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

import com.example.trabajo_firebase.modelo.jugador
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class ViewModelFirebase : ViewModel() {

    // conexion mysql...
    val conexion = FirebaseFirestore.getInstance()


    // Lista de toda la grifa, que la actualizará Firebase.
    private var _listaGrifa =
        MutableStateFlow(mutableStateListOf<jugador>())
    var listaGrifa = _listaGrifa.asStateFlow()


    fun crearListener(){

        // ponemos la oreja
        conexion.collection("Grifa").addSnapshotListener{
                datos, error ->
            if(error == null) {
                // ¿Que cambios nuevos ha habido en la BBDD?
                datos?.documentChanges?.forEach { cambio ->
                    if(cambio.type == DocumentChange.Type.ADDED) {
                        println("${cambio.document.data}")
                        var dinero = cambio.document.data.get("nombre")
                        println(dinero)
                    }
                    else if(cambio.type == DocumentChange.Type.REMOVED) {
                        // borramos elemento de la lista UI
                    }
                    else if(cambio.type == DocumentChange.Type.MODIFIED) {
                        // modificamos elemento de la lista UI
                    }

                }
            }
        }
    }
}