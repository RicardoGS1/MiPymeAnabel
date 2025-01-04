package com.virtualworld.mipymeanabel.data.dto

data class Order(

    val name : String,
    val dateOrder: String,
    val dateDelivery: String,
    val listOrderProducts: List<OrderProducts> = emptyList(),

)

data class OrderProducts(

    val idp: String = "",
    val name: String = "",
    val priceMn: String = "",
    val priceUsd: String = "",
    val detail:String = "",
    val available:String = "",
    val image:String = "",
    val category:String = "",
    val unit: String

)