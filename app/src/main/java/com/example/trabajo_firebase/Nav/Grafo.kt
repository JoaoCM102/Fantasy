package com.example.trabajo_firebase.Nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trabajo_firebase.Música.ExoPlayerViewModel
import com.example.trabajo_firebase.Rutas.Rutas
import com.example.trabajo_firebase.ventanas.Ajustes
import com.example.trabajo_firebase.ventanas.ContraseñaOlvidada
import com.example.trabajo_firebase.ventanas.MenuInicio
import com.example.trabajo_firebase.ventanas.MenuPrincipal
import com.example.trabajo_firebase.ventanas.NoticiaDetallada
import com.example.trabajo_firebase.ventanas.Noticias
import com.example.trabajo_firebase.ventanas.Registro
import com.example.trabajo_firebase.ventanas.subirNoticia

@Composable
fun GrafoNavegacion(context: Context){
    val navController = rememberNavController()
    val exoPlayerViewModel: ExoPlayerViewModel = viewModel()

    NavHost(navController = navController, startDestination = Rutas.MenuPrincipal.ruta) {

        // "URL" -> Composable
        composable(Rutas.MenuPrincipal.ruta){
            MenuPrincipal(navController = navController, context =  context, exoPlayerViewModel = exoPlayerViewModel)
        }

        composable(Rutas.Ajustes.ruta){
            Ajustes(navController = navController, context =  context, exoPlayerViewModel = exoPlayerViewModel)
        }
        composable(Rutas.CambiarContraseña.ruta){
            ContraseñaOlvidada().ContraseñaOlvidada(navController = navController, context =  context, exoPlayerViewModel = exoPlayerViewModel)
        }
        composable(Rutas.Registro.ruta){
            Registro().Registro(navController = navController, context =  context)
        }
        composable(Rutas.MenuInicio.ruta){
            MenuInicio().Inicio(navController = navController, context =  context, exoPlayerViewModel = exoPlayerViewModel)
        }

        composable(Rutas.Noticias.ruta){
            Noticias(navController = navController)
        }
        composable(Rutas.Noticia.ruta){
            NoticiaDetallada(navController = navController)
        }
        composable(Rutas.SubirNoticia.ruta){
            subirNoticia(navController = navController)
        }



    }
}


