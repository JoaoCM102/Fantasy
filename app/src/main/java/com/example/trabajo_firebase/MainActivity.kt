package com.example.trabajo_firebase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trabajo_firebase.Nav.GrafoNavegacion
import com.example.trabajo_firebase.firebase.ViewModelFirebase
import com.example.trabajo_firebase.ui.theme.Trabajo_FireBaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Trabajo_FireBaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: ViewModelFirebase = viewModel()
                    viewModel.crearListener()

                    GrafoNavegacion(context = this)

                }
            }
        }
    }
}

