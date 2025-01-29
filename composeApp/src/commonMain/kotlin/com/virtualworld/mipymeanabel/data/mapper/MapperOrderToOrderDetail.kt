package com.virtualworld.mipymeanabel.data.mapper

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import com.virtualworld.mipymeanabel.domain.models.OrderDetail

fun Order.toOrderDetail() : OrderDetail {

    return OrderDetail(

        number = this.number,
        state = this.state,
        dateOrder = this.dateOrder,
        dateDelivery = this.dateDelivery,
        importTotalUSD = calculateTotalImport(this.listOrderProducts).toString(),
        importTotalMN = calculateTotalImportMN(this.listOrderProducts).toString(),
        unitTotal =  calculateTotalUnit(this.listOrderProducts) .toString(),
        listOrderProducts = this.listOrderProducts

    )






}

fun calculateTotalUnit(listOrderProducts: List<OrderProducts>): Float {

    var totalUsd = 0.0f

    listOrderProducts.forEach { product ->
        totalUsd += product.unit.toInt()
    }
    return totalUsd
}

fun calculateTotalImport(listOrderProducts: List<OrderProducts>): Float {

    var totalUsd = 0.0f

    listOrderProducts.forEach { product ->
        val priceUsd = product.priceUsd.toFloatOrNull() ?: 0.0f
        totalUsd += priceUsd * product.unit.toInt()
    }
    return totalUsd
}

fun calculateTotalImportMN(listOrderProducts: List<OrderProducts>): Float {

    var totalUsd = 0.0f

    listOrderProducts.forEach { product ->
        val priceUsd = product.priceMn.toFloatOrNull() ?: 0.0f
        totalUsd += priceUsd * product.unit.toInt()
    }
    return totalUsd
}