package com.example.trabajo_firebase.Nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trabajo_firebase.Rutas.Rutas
import com.example.trabajo_firebase.ventanas.ContraseñaOlvidada
import com.example.trabajo_firebase.ventanas.MenuPrincipal
import com.example.trabajo_firebase.ventanas.Registro

@Composable
fun GrafoNavegacion(context: Context){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Rutas.MenuPrincipal.ruta) {

        // "URL" -> Composable
        composable(Rutas.MenuPrincipal.ruta){
            MenuPrincipal().MenuPrincipal(navController = navController)
        }

        composable(Rutas.AñadirJugador.ruta){
            //AñadirJugador(navController = navController)
        }
        composable(Rutas.CambiarContraseña.ruta){
            ContraseñaOlvidada().ContraseñaOlvidada(navController = navController)
        }
        composable(Rutas.Registro.ruta){
            Registro().Registro(navController = navController)
        }



    }
}


