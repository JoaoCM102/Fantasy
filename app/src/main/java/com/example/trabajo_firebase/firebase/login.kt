package com.example.trabajo_firebase.firebase

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trabajo_firebase.firebase.clases.Users
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch
import java.io.StringReader

class login : ViewModel() {
    private val auth: FirebaseAuth = Firebase.auth

    //esta de abajo lo que hace es evitar que se creen varios a la vez
    private val _loading = MutableLiveData(false)

    fun loginMeter(email: String, passsword: String, home: () -> Unit) = viewModelScope.launch {
        try {
            auth.signInWithEmailAndPassword(email, passsword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("logueado","log")
                        home()
                        Users.valorAutor="${task.result.user?.email?.split("@")?.get(0)}"

                    }else{
                        Log.d("logueado","log: ${task.result.toString()}")
                    }
                }

        }
        catch (ex: Exception){
            Log.d("logueado","log: ${ex.message}")

        }
    }
    @SuppressLint("SuspiciousIndentation")
    fun crearUsuario(email: String, passsword: String, home: () -> Unit){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email,passsword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        home()
                        val displayName =
                            task.result.user?.email?.split("@")?.get(0)
                            Users.valorAutor="${task.result.user?.email?.split("@")?.get(0)}"
                            createUser(displayName)
                    }else{
                        Log.d("registro","crear ${task.result}")
                        _loading.value = false
                    }
                }
        }
    }

    private fun createUser(displayName: String?) {
        val userId = auth.currentUser?.uid
        val user = Users(
            userId = userId.toString(),
            displayName = displayName.toString(),
            avatarUrl = "",
            numNoticias = 0,
            valoracion = 0f,
            id = null
        ).toMap()
        FirebaseFirestore.getInstance().collection("users")
            .add(user)
            .addOnSuccessListener {
                Log.d("registro","creado ${it.id}")
            }.addOnFailureListener {
                Log.d("registro","error ${it}")
            }

    }
}