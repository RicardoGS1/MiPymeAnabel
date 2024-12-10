package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.virtualworld.mipymeanabel.navegation.RouteCart
import com.virtualworld.mipymeanabel.navegation.RouteDetail
import com.virtualworld.mipymeanabel.navegation.RouteFavorite
import com.virtualworld.mipymeanabel.navegation.RouteHome
import com.virtualworld.mipymeanabel.navegation.RouteProfile

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarView(scrollBehavior: TopAppBarScrollBehavior, navController: NavHostController) {

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

// Mapa de rutas a títulos
    val routeTitles = mapOf(
        RouteHome.route to RouteHome.title,
        RouteCart.route to RouteCart.title,
        RouteProfile.route to RouteProfile.title,
        RouteFavorite.route to RouteFavorite.title,
        RouteDetail.route + "/{productId}" to RouteDetail.title
    )

    val currentTitle = routeTitles[currentRoute] ?: "Anabella´s Shop"

    CenterAlignedTopAppBar(
        title = { Text(text = currentTitle, ) },
        scrollBehavior = scrollBehavior,
        navigationIcon = {
            if (currentRoute == RouteDetail.route+"/{productId}") {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        }
    )

}