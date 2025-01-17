package com.virtualworld.mipymeanabel.navegation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.virtualworld.mipymeanabel.ui.screen.main.BottomNavItem

fun NavController.navigateToBottomNavDestination(item: BottomNavItem) {

    navigate(item.route) {

        if (this@navigateToBottomNavDestination.currentBackStackEntry?.destination?.route == RouteDetail.route + "/{productId}") {
            popBackStack()
        }

        popUpTo(graph.findStartDestination().route!!) {
            this.saveState = true
        }


        restoreState = true
        launchSingleTop = true

    }

}

fun NavController.navigateToDetailDestination(screen: String) {

    navigate(screen) {
        saveState()
    }

}

