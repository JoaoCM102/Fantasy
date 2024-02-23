package com.example.trabajo_firebase.ventanas

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.firebase.ViewModelComentario
import com.example.trabajo_firebase.firebase.ViewModelFirebaseNoticias
import com.example.trabajo_firebase.firebase.clases.Comentarios
import com.example.trabajo_firebase.firebase.clases.Users


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun NoticiaDetallada(navController : NavController) {

    var viewModelNoticia: ViewModelFirebaseNoticias = viewModel()
    var listaNoticias = viewModelNoticia.listaGrifa.collectAsState().value
    var viewModelComentario: ViewModelComentario = viewModel()
    var listaComentario = viewModelComentario.listaGrifa.collectAsState().value
    var añadirComentario by remember { mutableStateOf("") }
    var imgCora by remember { mutableStateOf(R.drawable.corazon_vacio) }
    var pulsado by remember { mutableStateOf(false) }
    if (pulsado == false){
        imgCora = R.drawable.corazon_vacio
    }else{
        imgCora = R.drawable.corazon_lleno
    }
    DisposableEffect(key1 = viewModelNoticia){
        viewModelNoticia.crearListener()
        onDispose { viewModelNoticia.borrarListener() }
    }
    DisposableEffect(key1 = viewModelComentario){
        viewModelComentario.crearListener()
        onDispose { viewModelComentario.borrarListener() }
    }


    Column(modifier = Modifier.fillMaxSize()) {
        LazyColumn() {
            //if () {
                items(listaNoticias) { noticia ->
                    if (listaNoticias.indexOf(noticia) == Users.idNoticia){
                        Image(
                        painterResource(id = R.drawable.caramessi),
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .width(200.dp)
                    )
                        Text(text = "${noticia.titulo}")
                        Text(text = "Autor: ${noticia.autor}")
                        Text(text = "Encabezado : ${noticia.encabezado}")
                        Text(text = "Texto :${noticia.texto}")}

                }
            //}

        }
        LazyColumn() {
            items(listaComentario) { comentario ->
                comentario
                var imgCora by remember { mutableStateOf(R.drawable.corazon_vacio) }
                var pulsado by remember { mutableStateOf(false) }
                var pulsadoLargo by remember { mutableStateOf(true) }
                var showDialogBorrar by remember { mutableStateOf(false) }
                var showDialogModificar by remember { mutableStateOf(false) }
                if (pulsado == false){
                    imgCora = R.drawable.corazon_vacio
                }else{
                    imgCora = R.drawable.corazon_lleno
                }
                if (pulsadoLargo){
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.White)
                        .combinedClickable(onClick = {}, onLongClick = {
                            pulsadoLargo = false
                            println("funciona")
                            println(pulsadoLargo)
                        })){
                        Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                            Column {
                                Row() {
                                    Text(text = "Usuario : ${comentario.autor}")
                                }
                                Text(text = "${comentario.comentario}" )
                            }
                            Column(modifier = Modifier.size(25.dp)) {
                                Icon(painterResource(id = imgCora), contentDescription = null,
                                    modifier = Modifier.clickable { !pulsado })
                            }
                        }



                    }
                }else{
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color.Gray)
                        .combinedClickable(onClick = {}, onLongClick = { pulsadoLargo = true })){
                        Row(horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
                            Icon(painterResource(id = R.drawable.trush_square_icon_257909), contentDescription = "borra",
                                modifier = Modifier
                                    .clickable { showDialogBorrar = true }
                                    .size(40.dp))
                            Icon(painterResource(id = R.drawable.editar), contentDescription = null, modifier = Modifier
                                .clickable { showDialogModificar = true }
                                .size(40.dp))
                        }
                    }
                }

                if (showDialogBorrar == true) {
                    AlertDialog(
                        onDismissRequest = { showDialogBorrar = false },
                        title = { Text("Estas seguro de borrar el comentario") },
                        text = { Text("No puedes volver una vez borrado") },
                        confirmButton = {
                            TextButton(onClick = { viewModelComentario.borrarComentario(comentario)}) {
                                Text("Borrar".uppercase())
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialogBorrar = false }) {
                                Text("Cancelar".uppercase())
                            }
                        },
                    )
                }
                if (showDialogModificar == true) {
                    var text by remember { mutableStateOf("") }
                    AlertDialog(
                        onDismissRequest = { showDialogBorrar = false },
                        title = { Text("Estas seguro de borrar el comentario") },
                        text = { Text("Nuevo Comentario")
                               TextField(value = text, onValueChange = {text = it})},
                        confirmButton = {
                            TextButton(onClick = { viewModelComentario.actualizarComentario(comentario,text)}) {
                                Text("Aceptar".uppercase())
                            }
                        },
                        dismissButton = {
                            TextButton(onClick = { showDialogBorrar = false }) {
                                Text("Cancelar".uppercase())
                            }
                        },
                    )
                }

            }
        }
        Column {
            TextField(value = añadirComentario, onValueChange = {añadirComentario = it})
            Button(onClick = {viewModelComentario.anyadirComentario(añadirComentario)}) {
                Text(text = "Añadir")
            }
        }
    }
}
@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
fun caja(){
    var imgCora by remember { mutableStateOf(R.drawable.corazon_vacio) }
    var pulsado by remember { mutableStateOf(false) }
    var pulsadoLargo by remember { mutableStateOf(true) }
    if (pulsado == false){
        imgCora = R.drawable.corazon_vacio
    }else{
        imgCora = R.drawable.corazon_lleno
    }
    if (pulsadoLargo){
        Box(modifier = Modifier
            .size(200.dp, 40.dp)
            .background(Color.White)
            .combinedClickable(onClick = {}, onLongClick = { !pulsadoLargo })){
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
                Column {
                    Row() {
                        Text(text = "Usuario :", fontSize = 9.sp)
                    }
                    Text(text = "" , fontSize = 9.sp)
                }
                Column(modifier = Modifier.size(25.dp)) {
                    Icon(painterResource(id = imgCora), contentDescription = null,
                        modifier = Modifier.clickable { !pulsado })
                }
            }



        }
    }else{
        Box(modifier = Modifier
            .size(200.dp, 40.dp)
            .background(Color.Gray)
            .combinedClickable(onClick = {}, onLongClick = { !pulsadoLargo })){
            Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                Icon(painterResource(id = R.drawable.corazon_vacio), contentDescription = null, modifier = Modifier.clickable {  })
                Icon(painterResource(id = R.drawable.corazon_lleno), contentDescription = null, modifier = Modifier.clickable {  })
            }
        }
    }

}
