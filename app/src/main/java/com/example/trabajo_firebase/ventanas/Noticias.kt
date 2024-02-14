package com.example.trabajo_firebase.ventanas

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.Rutas.Rutas

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun Noticias(navController : NavController){
    var textoSearchBar by remember { mutableStateOf("") }
    var activeSearchBar by remember { mutableStateOf(false) }
    var filtro by remember { mutableStateOf("") }
    var contextMenuPhotoId by rememberSaveable { mutableStateOf<Int?>(null) }
    val haptics = LocalHapticFeedback.current
    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = textoSearchBar,
            onQueryChange = { textoSearchBar = it },
            onSearch = { activeSearchBar = false },
            active = activeSearchBar,
            onActiveChange = { activeSearchBar = it }){

        }
        LazyColumn(){
        }
        Box(modifier = Modifier
            .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
            .combinedClickable(
            onClick = {navController.navigate(Rutas.Noticia.ruta)},
            onLongClick = {haptics.performHapticFeedback(HapticFeedbackType.LongPress)},
        )) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(painterResource(id = R.drawable.caramessi), contentDescription = null,
                    Modifier
                        .width(70.dp)
                        .height(70.dp))
                Column {
                    Text(text = "Messi es el mejor del mundo y aqui te explicamos el porque")
                    Text(text = "Autor: Juan de la aventura")
                }
            }

        }

    }

}
