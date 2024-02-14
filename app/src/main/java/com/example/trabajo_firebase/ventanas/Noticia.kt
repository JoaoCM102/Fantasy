package com.example.trabajo_firebase.ventanas

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajo_firebase.R


@Composable
fun NoticiaDetallada(navController : NavController){
    Column {
        Image(painterResource(id = R.drawable.caramessi), contentDescription = null, modifier = Modifier
            .height(200.dp)
            .width(200.dp))
        Text(text = "Autor: Juan")
        Text(text = "Messi es considerado por muchos como el mejor jugador del mundo por varias razones. En primer lugar, su habilidad técnica es excepcional. Su control del balón, su dribbling y su capacidad para regatear a varios defensores son inigualables. Además, su visión de juego y su capacidad para pasar el balón son de otro nivel. Messi también es un goleador prolífico, capaz de marcar goles de todas las formas y distancias.")

    }
}
