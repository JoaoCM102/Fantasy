package com.example.trabajo_firebase.ventanas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.Rutas.Rutas
import com.example.trabajo_firebase.firebase.ViewModelFirebaseNoticias
import com.example.trabajo_firebase.firebase.clases.Noticias
import com.example.trabajo_firebase.firebase.clases.Users
import kotlinx.coroutines.flow.asFlow

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Noticias(navController : NavController){
    var textoSearchBar by remember { mutableStateOf("") }
    var viewModelNoticia : ViewModelFirebaseNoticias = viewModel()
    var listaGrifaUINoticia = viewModelNoticia.listaGrifa.collectAsState().value
    var activeSearchBar by remember { mutableStateOf(false) }
    var filtro by remember { mutableStateOf("") }
    var contextMenuPhotoId by rememberSaveable { mutableStateOf<Int?>(null) }



   DisposableEffect(key1 = viewModelNoticia){
        viewModelNoticia.crearListener()
       onDispose { viewModelNoticia.borrarListener() }
    }

    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = textoSearchBar,
            onQueryChange = { textoSearchBar = it },
            onSearch = { activeSearchBar = false },
            active = activeSearchBar,
            onActiveChange = { activeSearchBar = it }){
            val filtrarAutor: List<Noticias> = listaGrifaUINoticia.filter { it.autor.contains(textoSearchBar, true) }
            for (i in filtrarAutor.indices) {
                Row(horizontalArrangement = Arrangement.Center) {
                    Box(modifier = Modifier.clickable { textoSearchBar = filtrarAutor[i].autor }) {
                        Text(text = filtrarAutor[i].autor, modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp))
                    }

                }

            }
        }
        LazyColumn(){
            items(listaGrifaUINoticia){noticia->
                if (noticia.autor == textoSearchBar){
                    Box(modifier = Modifier
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                        .combinedClickable(
                            onClick = {
                                navController.navigate(Rutas.Noticia.ruta)
                                Users.idNoticia = listaGrifaUINoticia.indexOf(noticia)
                            }
                        )) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painterResource(id = R.drawable.caramessi), contentDescription = null,
                                Modifier
                                    .width(70.dp)
                                    .height(70.dp))
                            Column {
                                Text(text = "Titulo: ${noticia.titulo}")
                                Text(text = "Autor: ${noticia.autor}")
                                Text(text = "Texto: ${noticia.texto}")
                            }
                        }

                    }
                }else if (textoSearchBar == ""){
                    Box(modifier = Modifier
                        .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
                        .combinedClickable(
                            onClick = {
                                navController.navigate(Rutas.Noticia.ruta)
                                Users.idNoticia = listaGrifaUINoticia.indexOf(noticia)
                            }
                        )) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Image(painterResource(id = R.drawable.caramessi), contentDescription = null,
                                Modifier
                                    .width(70.dp)
                                    .height(70.dp))
                            Column {
                                Text(text = "Titulo: ${noticia.titulo}")
                                Text(text = "Autor: ${noticia.autor}")
                                Text(text = "Texto: ${noticia.texto}")
                            }
                        }

                    }
                }

            }
        }


    }

}
