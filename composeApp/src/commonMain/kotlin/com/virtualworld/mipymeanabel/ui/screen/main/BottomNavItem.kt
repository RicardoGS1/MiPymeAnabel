package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.virtualworld.mipymeanabel.navegation.RouteCart
import com.virtualworld.mipymeanabel.navegation.RouteFavorite
import com.virtualworld.mipymeanabel.navegation.RouteHome
import com.virtualworld.mipymeanabel.navegation.RouteProfile

sealed class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
)
{
    data object Home : BottomNavItem(
        title = "Catalogo",
        icon = Icons.Default.Home,
        route = RouteHome.route,
    )

    data object Cart : BottomNavItem(
        title = "Ordenes",
        icon = Icons.Default.ShoppingCart,
        route = RouteCart.route,
    )

    data object Profile : BottomNavItem(
        title = "Usuario",
        icon = Icons.Default.AccountCircle,
        route = RouteProfile.route,
    )

    companion object {
        fun getDestinations(): List<BottomNavItem> {
            return listOf(
                Home,
                Cart,
                Profile,
            )
        }
    }
}