package com.example.trabajo_firebase.ventanas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.firebase.ViewModelFirebase
import com.example.trabajo_firebase.firebase.ViewModelFirebaseNoticias
import com.example.trabajo_firebase.firebase.clases.Users


@Composable
fun Perfil(navController: NavController){
    var viewModel : ViewModelFirebase = viewModel()
    DisposableEffect(key1 = viewModel){
        viewModel.crearListener()
        onDispose { viewModel.borrarListener() }
    }
    var listaGrifaUI = viewModel.listaGrifa.collectAsState().value
    var listaGrifaUINoticia = viewModel.listaGrifa.collectAsState().value

    Column(modifier = Modifier
        .fillMaxSize()
        .background(color = Color.White)) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()){
            items(listaGrifaUINoticia){perfil->
                if (perfil.displayName == Users.valorAutor)
                Column {
                    Image(painterResource(id = R.drawable.caramessi), contentDescription = null)
                    Text(text = "Nombre autor : ${perfil.displayName}")
                    Text(text = "Numero de noticias que tienes publicados : ${perfil.numNoticias}")
                    Text(text = "Valoracion de tu perfil  : ${perfil.valoracion}")
                }
            }
        }
    }
}