package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun HomeScreen(){

LazyColumn (Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

    for (i in 1..30) {


        item {
            Button(onClick = { println("Hola Mundo$i") }) {
                Text("Hola Mundo$i")
            }
        }
    }
}}
