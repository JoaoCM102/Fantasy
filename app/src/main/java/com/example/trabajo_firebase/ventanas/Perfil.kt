package com.example.trabajo_firebase.ventanas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.trabajo_firebase.firebase.ViewModelFirebase
import com.example.trabajo_firebase.firebase.ViewModelFirebaseNoticias

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Perfil(navController: NavController){
    var viewModel : ViewModelFirebase = viewModel()
    var viewModelNoticia : ViewModelFirebaseNoticias = viewModel()
    DisposableEffect(key1 = viewModel){
        viewModel.crearListener()
        onDispose { viewModel.borrarListener() }
    }
    DisposableEffect(key1 = viewModelNoticia){
        viewModelNoticia.crearListener()
        onDispose { viewModelNoticia.borrarListener() }
    }
    var listaGrifaUI = viewModel.listaGrifa.collectAsState().value
    var listaGrifaUINoticia = viewModelNoticia.listaGrifa.collectAsState().value
    Column {
        /*LazyColumn(){
            items(listaGrifaUI){
                Text(text = it.toString())
            }
        }*/
        LazyColumn(){
            items(listaGrifaUINoticia){
                Text(text = it.toString())
            }
        }
    }
}