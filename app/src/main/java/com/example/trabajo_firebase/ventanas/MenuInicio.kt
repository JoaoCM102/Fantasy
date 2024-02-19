package com.example.trabajo_firebase.ventanas

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajo_firebase.Música.ExoPlayerViewModel
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.Rutas.Rutas

class MenuInicio {
    @Composable
    fun Inicio(navController : NavController, context : Context, exoPlayerViewModel: ExoPlayerViewModel){
        Box(modifier = with(Modifier) {
            fillMaxSize()
                .paint(
                    // Replace with your image id
                    painterResource(id = R.drawable.campo_de_futbol),
                    contentScale = ContentScale.FillBounds
                )

        }) {
            Column(modifier = Modifier.fillMaxSize(),verticalArrangement = Arrangement.SpaceEvenly
                , horizontalAlignment = Alignment.CenterHorizontally) {
                Image(
                    painterResource(id = R.drawable.sports_logofootball_logo_game_logo_socccer___hecho_con_postermywall),
                    contentDescription = null,
                    modifier = Modifier
                        .height(150.dp)
                        .width(150.dp)
                )
                cajas(imagen = R.drawable.caramessi,300,120,"Noticias",accion = {navController.navigate(Rutas.Noticias.ruta)})
                cajas(imagen = R.drawable.equipo,300,120,"Subir Noticia",accion = {navController.navigate(Rutas.SubirNoticia.ruta)})
                cajas(imagen = R.drawable.investigacion,300,120,"Perfil",accion = {navController.navigate(Rutas.Perfil.ruta)})
                cajas(imagen = R.drawable.ajuste,300,120,"Ajustes", accion = {navController.navigate(Rutas.Ajustes.ruta)})
            }
        }
    }

    @Composable
    fun cajas(imagen: Int, with: Int, height: Int, Texto: String,accion : () -> Unit){
        Box(modifier = Modifier.clickable { accion() }
            .background(Color.White.copy(alpha = 0.5f))
            .width(with.dp)
            .height(height.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceEvenly, modifier = Modifier.fillMaxSize()) {
                Image(painterResource(id = imagen), contentDescription = null, modifier = Modifier
                    .width(110.dp)
                    .height(110.dp))
                Text(text = Texto, textAlign = TextAlign.Center, modifier = Modifier)
            }
        }
    }


}