package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView() {

    TopAppBar(title = { Text(text = "MiPymeAnabel") },)

}