package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.compose.rememberNavController
import com.virtualworld.mipymeanabel.navegation.AppNavHost

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {

    val navController = rememberNavController()


    Surface(modifier = Modifier.fillMaxSize()) {
        val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

        Scaffold(
            bottomBar = { BottomBarView(navController) },
            topBar = { TopBarView(scrollBehavior) },
            modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
        ) { paddingValues ->

            AppNavHost(navController, paddingValues)


        }
    }
}