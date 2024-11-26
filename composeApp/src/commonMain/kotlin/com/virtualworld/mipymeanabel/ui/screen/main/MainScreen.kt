package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

