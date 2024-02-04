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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trabajo_firebase.Música.ExoPlayerViewModel
import com.example.trabajo_firebase.R

@Composable
fun Ajustes(navController : NavController, context : Context, exoPlayerViewModel: ExoPlayerViewModel) {
    var sliderValue by remember { mutableStateOf(0f) }

    Box(modifier = with(Modifier) {
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.campo_de_futbol),
                contentScale = ContentScale.FillBounds
            )

    }){
        Box(modifier = Modifier
            .clickable { }
            .background(Color.White.copy(alpha = 0.7f))
            .width(300.dp)
            .height(500.dp)
            .align(Alignment.Center)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally,


                modifier = Modifier.fillMaxSize()
            ) {
                //Item y texto
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Ajustes")


                }

                //Item y texto
                Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 10.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                    Text(text = "Volumen")
                    //Volumen
                    Slider(modifier = Modifier.padding(horizontal = 10.dp),
                        value = sliderValue,
                        onValueChange = { newValue ->
                            sliderValue = newValue
                            exoPlayerViewModel.cambiarVolumen(context, newValue)
                        },
                        valueRange = 0f..1f,
                        steps = 100
                        , colors = SliderDefaults.colors(
                            thumbColor = Color(63, 81, 181, 255),
                            activeTickColor = Color(63, 81, 181, 255),
                            activeTrackColor =Color(63, 81, 181, 255),
                            inactiveTickColor = Color.Gray,
                            inactiveTrackColor = Color.Gray
                        )
                    )

                }




                //Último
                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(3.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Volver")
                }

            }
        }
    }



}


