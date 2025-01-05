package com.virtualworld.mipymeanabel.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Order(

    val name : String,
    val dateOrder: String,
    val dateDelivery: String,
    val listOrderProducts: List<OrderProducts> = emptyList(),

)

@Serializable
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