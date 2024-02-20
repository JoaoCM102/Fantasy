package com.example.trabajo_firebase.ventanas

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.trabajo_firebase.firebase.ViewModelFirebaseNoticias

@Composable
fun subirNoticia(navController : NavController){
    var viewModel : ViewModelFirebaseNoticias = viewModel()
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        var titulo by remember { mutableStateOf("") }
        var encabezado by remember { mutableStateOf("") }
        var text by remember { mutableStateOf("") }
        Text(text = "Titulo de la noticia", color = Color.Black)
        TextField(
            value = titulo,
            onValueChange = { titulo = it }, modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
        )
        Text(text = "Encabezado de la noticia", color = Color.Black)
        TextField(
            value = encabezado,
            onValueChange = { encabezado = it }, modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
        )
        Text(text = "Texto de la noticia", color = Color.Black)
        TextField(
            value = text,
            onValueChange = { text = it }, modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .padding(10.dp)
                .border(width = 1.dp, color = Color.Black, shape = RoundedCornerShape(8.dp))
        )
        Button(
            onClick = { viewModel.anyadirNoticia(titulo,text) },
            modifier = Modifier
                .padding(3.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            )
        ) {
            Text(text = "Subir")
        }
    }
}
