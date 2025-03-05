package com.virtualworld.mipymeanabel.data.mapper

import com.virtualworld.mipymeanabel.data.dto.Product
import com.virtualworld.mipymeanabel.domain.models.ProductAll

fun Product.toProductAll(favorite: Boolean?, cart: Boolean?): ProductAll {

    return ProductAll(
        idp = this.idp,
        name = this.name,
        priceMn = this.priceMn,
        priceUsd = this.priceUsd,
        detail = this.detail,
        available = this.available,
        image = this.image,
        category = this.category,
        favorite = favorite ?: false,
        cart = cart ?: false,
    )
}