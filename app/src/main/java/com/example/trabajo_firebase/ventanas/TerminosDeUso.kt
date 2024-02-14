package com.example.trabajo_firebase.ventanas

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.trabajo_firebase.Música.ExoPlayerViewModel
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.VerticalPdfReaderState

@Composable
fun TerminosDeUso(
    navController: NavController, context: Context, exoPlayerViewModel: ExoPlayerViewModel, verticalPdfReaderState: VerticalPdfReaderState){
    VerticalPDFReader(state = verticalPdfReaderState, modifier = Modifier )
}