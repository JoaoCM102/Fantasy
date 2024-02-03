package com.example.trabajo_firebase.firebase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
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
                    }else{
                        Log.d("logueado","log: ${task.result.toString()}")
                    }
                }

        }
        catch (ex: Exception){
            Log.d("logueado","log: ${ex.message}")

        }
    }
    fun crearUsuario(email: String, passsword: String, home: () -> Unit){
        if (_loading.value == false){
            _loading.value = true
            auth.createUserWithEmailAndPassword(email,passsword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        home()

                    }else{
                        Log.d("registro","crear ${task.result}")
                        _loading.value = false
                    }
                }
        }
    }
}