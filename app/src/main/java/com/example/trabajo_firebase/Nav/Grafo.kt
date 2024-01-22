package com.example.trabajo_firebase.Nav

import android.content.Context
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionContext
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trabajo_firebase.Rutas.Rutas
import com.example.trabajo_firebase.ventanas.MenuPrincipal

@Composable
fun GrafoNavegacion(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.MenuPrincipal.ruta) {

        // "URL" -> Composable
        composable(Rutas.MenuPrincipal.ruta){
            MenuPrincipal().MenuPrincipal()
        }

        composable(Rutas.AñadirJugador.ruta){
            //AñadirJugador(navController = navController)
        }



    }
}


