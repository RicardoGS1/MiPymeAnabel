package com.virtualworld.mipymeanabel.data.mapper

import com.virtualworld.mipymeanabel.data.dto.Product
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import kotlin.text.replace
import kotlin.text.toFloat

fun Product.toProductCart(): ProductCart {

    val priceUSDFloat = this.priceUsd.replace(',', '.').toFloat()
    val priceMNFloat = this.priceUsd.replace(',', '.').toFloat()

    return ProductCart(
        idp = this.idp.toLong(),
        name = this.name,
        priceMN = priceMNFloat,
        priceUSD = priceUSDFloat,
        image = this.image
    )
}
