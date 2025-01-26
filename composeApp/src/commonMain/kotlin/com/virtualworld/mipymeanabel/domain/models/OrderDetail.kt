package com.virtualworld.mipymeanabel.domain.models

import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import kotlinx.serialization.Serializable

data class OrderDetail(

    val number : String = "",
    val state : String = "",
    val dateOrder: String = "",
    val dateDelivery: String = "",
    val importTotal: String = "",
    val unitTotal: String = "",
    val listOrderProducts: List<OrderProducts> = emptyList(),

    )



