package com.example.trabajo_firebase.ventanas

import android.annotation.SuppressLint
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.trabajo_firebase.R
import com.example.trabajo_firebase.Rutas.Rutas
import com.example.trabajo_firebase.firebase.login


class Registro () {
    @SuppressLint("NotConstructor")
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Registro(navController : NavController,
                 viewModel: login = androidx.lifecycle.viewmodel.compose.viewModel(), context : Context
    ) {


        var usuario by remember { mutableStateOf("") }
        var contraseña by remember { mutableStateOf("") }
        var contraseña2 by remember { mutableStateOf("") }
        var contraseñaVisibilidad by remember { mutableStateOf(false) }
        var colorTexto by remember { mutableStateOf(Color.White) }
        Box(
            modifier = with(Modifier) {
                fillMaxSize()
                    .paint(
                        // Replace with your image id
                        painterResource(id = R.drawable.desktop_wallpaper_55_soccer_field_soccer_pitch),
                        contentScale = ContentScale.FillBounds
                    )

            }) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Image(
                    painterResource(id = R.drawable.sports_logofootball_logo_game_logo_socccer___hecho_con_postermywall),
                    contentDescription = null
                )
            }
            Column(
                modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row {
                    Column {
                        TextField(value = usuario, onValueChange = { usuario = it }, label = {
                            Text(text = "Usuario")
                        }, modifier = Modifier.alpha(0.7f), leadingIcon = {
                            Icon(
                                painterResource(id = R.drawable.ic_person),
                                contentDescription = null
                            )
                        }, trailingIcon = {
                            if (usuario.isNotEmpty()) {
                                Icon(
                                    painterResource(id = R.drawable.ic_close),
                                    contentDescription = null,
                                    modifier = Modifier.clickable {
                                        usuario = ""
                                    })
                            }
                        }, colors = TextFieldDefaults.textFieldColors(

                        )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(20.dp))
                Row(Modifier.padding(0.dp, 0.dp, 0.dp, 40.dp)) {
                    Column {
                        TextField(value = contraseña,
                            onValueChange = { contraseña = it },
                            modifier = Modifier.alpha(0.7f),
                            label = {
                                Text(text = "Contraseña de nuevo")
                            },
                            leadingIcon = {
                                Icon(
                                    painterResource(id = R.drawable.ic_action_lock_closed),
                                    contentDescription = null
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(

                            ),
                            trailingIcon = {
                                if (contraseña.isNotEmpty()) {
                                    val visibility = if (contraseñaVisibilidad) {
                                        painterResource(id = R.drawable.ic_visibility)
                                    } else {
                                        painterResource(id = R.drawable.ic_visibility_off)
                                    }
                                    Icon(painter = visibility, contentDescription = null,
                                        modifier = Modifier.clickable {
                                            contraseñaVisibilidad = !contraseñaVisibilidad
                                        })
                                }
                            },
                            visualTransformation = if (contraseñaVisibilidad) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            )
                        )
                    }
                }

                Row(Modifier.padding(0.dp, 0.dp, 0.dp, 40.dp)) {
                    Column {
                        TextField(value = contraseña2,
                            onValueChange = { contraseña2 = it },
                            modifier = Modifier.alpha(0.7f),
                            label = {
                                Text(text = "Contraseña")
                            },
                            leadingIcon = {
                                Icon(
                                    painterResource(id = R.drawable.ic_action_lock_closed),
                                    contentDescription = null
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(

                            ),
                            trailingIcon = {
                                if (contraseña2.isNotEmpty()) {
                                    val visibility = if (contraseñaVisibilidad) {
                                        painterResource(id = R.drawable.ic_visibility)
                                    } else {
                                        painterResource(id = R.drawable.ic_visibility_off)
                                    }
                                    Icon(painter = visibility, contentDescription = null,
                                        modifier = Modifier.clickable {
                                            contraseñaVisibilidad = !contraseñaVisibilidad
                                        })
                                }
                            },
                            visualTransformation = if (contraseñaVisibilidad) VisualTransformation.None
                            else PasswordVisualTransformation(),
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.padding(0.dp, 0.dp, 0.dp, 0.dp))
                Row {
                    Button(
                        onClick = { if (contraseña.equals(contraseña2)){
                            viewModel.crearUsuario(usuario,contraseña){
                                navController.navigate(Rutas.MenuInicio.ruta)
                            }
                        } },
                        modifier = Modifier
                            .padding(3.dp)
                            .width(200.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black,
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Registrarse")
                    }

                }
                Button(
                    onClick = { },
                    modifier = Modifier
                        .padding(10.dp)
                        .width(200.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black,
                        contentColor = Color.White
                    )
                ) {
                    Text(text = "Volver")
                }




            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "Registrarse con:", color = Color.White)
                Row {
                    RedSocial(imageRes = R.drawable.buscar, modifier = Modifier)
                    RedSocial(imageRes = R.drawable.facebook, modifier = Modifier)
                    RedSocial(imageRes = R.drawable.logotipo_de_steam, modifier = Modifier)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "Terminos de Uso",
                        modifier = Modifier
                            .padding(0.dp, 60.dp, 0.dp, 13.dp)
                            .clickable(
                                onClick = { colorTexto = Color.Green }
                            ),
                        color = colorTexto)
                }
            }


        }
    }
}
