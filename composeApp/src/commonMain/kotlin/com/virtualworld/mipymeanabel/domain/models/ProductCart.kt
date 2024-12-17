package com.virtualworld.mipymeanabel.domain.models

data class ProductCart(
    val idp:Long,
    val name:String,
    val priceMN: Float,
    val priceUSD: Float,
    val image: String
)
