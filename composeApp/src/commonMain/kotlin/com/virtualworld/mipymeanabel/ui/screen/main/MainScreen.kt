package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.virtualworld.mipymeanabel.navegation.AppNavHost

@Composable
fun MainScreen() {

    val navController = rememberNavController()

    Surface(modifier = Modifier.fillMaxSize()) {


        Scaffold(
            bottomBar = { BottomBarView(navController) },
            topBar = { TopBarView() }
        ) { paddingValues ->

            AppNavHost(navController, paddingValues)


        }
    }
}