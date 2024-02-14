package com.example.trabajo_firebase.ventanas

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trabajo_firebase.Música.ExoPlayerViewModel
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.Rutas.Rutas
@Composable
fun Ajustes(navController: NavController, context: Context, exoPlayerViewModel: ExoPlayerViewModel) {
    var sliderValue by remember { mutableStateOf(0f) }
    Box(modifier = with(Modifier) {
        fillMaxSize()
            .paint(
                // Replace with your image id
                painterResource(id = R.drawable.campo_de_futbol),
                contentScale = ContentScale.FillBounds
            )

    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.8f))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Ajustes", style = TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold))

                Spacer(modifier = Modifier.height(16.dp))

                Text(text = "Cambiar Volumen", style = TextStyle( fontSize = 24.sp, fontWeight = FontWeight.Bold))
                Slider(
                    value = sliderValue,
                    onValueChange = { newValue ->
                        sliderValue = newValue
                        exoPlayerViewModel.cambiarVolumen(context, newValue)
                    },
                    valueRange = 0f..1f,
                    colors = SliderDefaults.colors(
                        thumbColor = Color(0, 0, 0, 255),
                        activeTickColor = Color(0, 0, 0, 255),
                        activeTrackColor = Color(0, 0, 0, 255),
                        inactiveTickColor = Color.Gray,
                        inactiveTrackColor = Color.Gray
                    ),
                    modifier = Modifier.padding(horizontal = 10.dp)
                )

                Button(
                    onClick = { exoPlayerViewModel.CambiarCancion(context) },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(200.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "Siguiente Canción")
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_skip_next_24),
                            contentDescription = null
                        )
                    }
                }

                Button(
                    onClick = { navController.navigate(Rutas.MenuInicio.ruta) },
                    modifier = Modifier
                        .padding(8.dp)
                        .width(200.dp)
                        .height(50.dp),
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
