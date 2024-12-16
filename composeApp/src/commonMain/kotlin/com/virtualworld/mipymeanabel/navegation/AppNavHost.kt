package com.virtualworld.mipymeanabel.navegation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.virtualworld.mipymeanabel.ui.screen.cart.CartScreen
import com.virtualworld.mipymeanabel.ui.screen.detail.DetailScreen
import com.virtualworld.mipymeanabel.ui.screen.home.HomeScreen
import com.virtualworld.mipymeanabel.ui.screen.profile.ProfileScreen
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun AppNavHost(navController: NavHostController, paddingValues: PaddingValues) {

    NavHost(
        navController,
        startDestination = RouteHome.route,
        modifier = Modifier.padding(paddingValues)
    ) {

        val onProductClicked: (String) -> Unit = { productId ->
            navController.navigate(RouteDetail.route + "/$productId")
        }

        composable(RouteHome.route) {
            HomeScreen(onProductClicked = onProductClicked)
        }
        composable(RouteCart.route) {
            CartScreen(cartViewModel = koinViewModel()  )
        }
        //+"/{productId}"
        composable(
            RouteDetail.route+"/{productId}",
            arguments = listOf(navArgument("productId") { type = NavType.StringType })
        ) { navBackStackEntry->
            val productId = navBackStackEntry.arguments?.getString("productId")
            DetailScreen(detailViewModel = koinViewModel(parameters = { parametersOf(productId) }))
        }
        composable(RouteProfile.route) {
            ProfileScreen()
        }

    }

}



