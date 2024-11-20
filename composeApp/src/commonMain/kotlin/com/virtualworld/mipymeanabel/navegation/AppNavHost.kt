package com.virtualworld.mipymeanabel.navegation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.virtualworld.mipymeanabel.ui.screen.cart.CartScreen
import com.virtualworld.mipymeanabel.ui.screen.favorite.FavoriteScreen
import com.virtualworld.mipymeanabel.ui.screen.home.HomeScreen
import com.virtualworld.mipymeanabel.ui.screen.profile.ProfileScreen

@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues)
{

    NavHost(navController,startDestination = RouteHome.route, modifier = Modifier.padding(paddingValues)) {



        composable(RouteHome.route) {
            HomeScreen()
        }
        composable(RouteCart.route) {
            CartScreen()
        }
        composable(RouteFavorite.route) {
            FavoriteScreen()
        }
        composable(RouteProfile.route) {
            ProfileScreen()
        }

    }


    }



