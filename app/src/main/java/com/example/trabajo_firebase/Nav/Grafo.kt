package com.example.trabajo_firebase.Nav

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.trabajo_firebase.Música.ExoPlayerViewModel
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.Rutas.Rutas
import com.example.trabajo_firebase.ventanas.Ajustes
import com.example.trabajo_firebase.ventanas.ContraseñaOlvidada
import com.example.trabajo_firebase.ventanas.MenuInicio
import com.example.trabajo_firebase.ventanas.MenuPrincipal
import com.example.trabajo_firebase.ventanas.Registro
import com.example.trabajo_firebase.ventanas.TerminosDeUso
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPdfReaderState
import com.rizzi.bouquet.rememberVerticalPdfReaderState

@Composable
fun GrafoNavegacion(context: Context){
    val navController = rememberNavController()
    val exoPlayerViewModel: ExoPlayerViewModel = viewModel()
    val TerminosDeUso = rememberVerticalPdfReaderState(
        resource = ResourceType.Asset(R.raw.terminosdeuso),
        isZoomEnable = true
    )
    LaunchedEffect(Unit) {
        exoPlayerViewModel.crearExoPlayer(context)
        exoPlayerViewModel.hacerSonarMusica(context)
        exoPlayerViewModel.toglearRandom()
    }

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
        composable(Rutas.Terminos.ruta){
            TerminosDeUso(
                navController = navController,
                context = context,
                exoPlayerViewModel = exoPlayerViewModel,
                verticalPdfReaderState = TerminosDeUso
            )
        }



    }
}


