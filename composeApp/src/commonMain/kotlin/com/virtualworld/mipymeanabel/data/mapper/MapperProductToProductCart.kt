package com.virtualworld.mipymeanabel.data.mapper

import com.virtualworld.mipymeanabel.data.dto.Product
import com.virtualworld.mipymeanabel.domain.models.ProductCart

fun Product.toProductCart(): ProductCart {

    return ProductCart(
        idp = this.idp.toLong(),
        name = this.name,
        priceMN = this.priceMn.toFloat(),
        priceUSD = this.priceUsd.toFloat(),
        image = this.image
    )
}
