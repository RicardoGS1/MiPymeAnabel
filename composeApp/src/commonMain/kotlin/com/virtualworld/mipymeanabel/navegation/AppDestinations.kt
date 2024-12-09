package com.virtualworld.mipymeanabel.navegation

interface AppDestinations {
    val route: String
}




object RouteHome : AppDestinations {
    override val route = "home"
}

object RouteCart : AppDestinations {
    override val route = "cart"
}

object RouteProfile : AppDestinations {
    override val route = "profile"
}

object RouteFavorite : AppDestinations {
    override val route = "favorite"
}

object RouteDetail : AppDestinations {
    override val route = "detail"
}