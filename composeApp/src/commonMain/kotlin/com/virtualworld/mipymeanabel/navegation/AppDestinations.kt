package com.virtualworld.mipymeanabel.navegation

interface AppDestinations {
    val route: String
    val title: String
}




object RouteHome : AppDestinations {
    override val route = "home"
    override val title = "Anabella´s Shop"
}

object RouteCart : AppDestinations {
    override val route = "cart"
    override val title = "Carrito"
}

object RouteProfile : AppDestinations {
    override val route = "profile"
    override val title = "Usuaria"
}

object RouteFavorite : AppDestinations {
    override val route = "favorite"
    override val title = "Favorito"
}

object RouteDetail : AppDestinations {
    override val route = "detail"
    override val title = "Detalles"
}

object RouteDetailOrder : AppDestinations {
    override val route = "detailOrder"
    override val title = "Detalles de la orden"
}