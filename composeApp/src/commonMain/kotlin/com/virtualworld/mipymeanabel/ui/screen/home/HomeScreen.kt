package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){

Column(Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
    Button(onClick = { println("Hola Mundo1") }) {
        Text("Hola Mundo1")
    }
    Button(onClick = { println("Hola Mundo2") }) {
        Text("Hola Mundo2")
    }
    Button(onClick = { println("Hola Mundo3") }) {
        Text("Hola Mundo3")
    }
}
}