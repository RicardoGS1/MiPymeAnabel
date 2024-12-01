package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(scrollBehavior: TopAppBarScrollBehavior) {



    TopAppBar(
        title = { Text(text = "MiPymeAnabel") },
        scrollBehavior = scrollBehavior,

    )

}