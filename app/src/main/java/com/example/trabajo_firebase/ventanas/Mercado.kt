package com.example.trabajo_firebase.ventanas

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.material3.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Mercado(){
    var textoSearchBar by remember { mutableStateOf("") }
    var activeSearchBar by remember { mutableStateOf(false) }
    var filtro by remember { mutableStateOf("") }
    Column {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = textoSearchBar,
            onQueryChange = { textoSearchBar = it },
            onSearch = { activeSearchBar = false },
            active = activeSearchBar,
            onActiveChange = { activeSearchBar = it }){}
    }

}
@Preview
@Composable
fun verr(){
    Mercado()
}