package com.virtualworld.mipymeanabel.data.dto

data class ProductAll(
    val idp: String,
    val name: String,
    val priceMn: String,
    val priceUsd: String,
    val detail:String,
    val available:String,
    val image:String,
    val category:String,
    val favorite:Boolean= false,
    val cart:Boolean= false
)
