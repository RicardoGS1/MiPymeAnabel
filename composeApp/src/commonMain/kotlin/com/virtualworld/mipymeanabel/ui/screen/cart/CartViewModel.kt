package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import com.virtualworld.mipymeanabel.domain.useCase.GetProductCartUseCase
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import com.virtualworld.mipymeanabel.domain.useCase.AddOrderUseCase
import com.virtualworld.mipymeanabel.domain.useCase.DeletCartUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.pow
import kotlin.math.round
import kotlin.text.padStart




class CartViewModel(
    private val getProductCartUseCase: GetProductCartUseCase,
    private val addOrderUseCase: AddOrderUseCase,
    private val deletCartUseCase: DeletCartUseCase,
) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductCart>>(emptyList())
    val productsState: StateFlow<List<ProductCart>> get() = _products

    private val _quantity = MutableStateFlow<Map<Long, Int>>(emptyMap())
    val quantity: StateFlow<Map<Long, Int>> get() = _quantity

    private val _totals = MutableStateFlow<Map<String, Float>>(emptyMap())
    val totals: StateFlow<Map<String, Float>> get() = _totals

    private val _dateDelivery = MutableStateFlow<Long>(0L)
    val dateDelivery: Flow<String> = _dateDelivery.map { millis ->
        if (millis == 0L) "" else convertMillisToDate(millis)
    }

    init {
        getProductsCart()
    }

    private fun getProductsCart() {

        viewModelScope.launch {

            getProductCartUseCase().collect { products ->

                _products.update {
                    products
                }

                for (product in products) {
                    if (_quantity.value[product.idp] == null) { //  _quantity.value.isEmpty())
                        _quantity.update {
                            products.associate { it.idp to 1 }
                        }
                    }

                }

                getTotals()

            }

        }
    }

    private fun getTotals() {

        var totalUSD = 0f
        var totalMN = 0f
        var units = 0f

        _products.value.forEach { prod ->

            val priceUSD = prod.priceUSD
            val priceMN = prod.priceMN

            totalUSD += priceUSD * _quantity.value[prod.idp]!!

            totalMN += priceMN * _quantity.value[prod.idp]!!

            units += _quantity.value[prod.idp]!!

        }


        _totals.update {
            mapOf("totalUSD" to totalUSD.roundToDecimals(2)) + mapOf(
                "totalMN" to totalMN.roundToDecimals(
                    2
                )
            ) + mapOf("units" to units)

        }


    }

    fun updateQuantity(idp: Long, unit: Int) {

        _quantity.update { currentQuantity ->

            val newQuantity = currentQuantity[idp]?.plus(unit) ?: 0
            if (newQuantity >= 0)
                currentQuantity.plus(idp to newQuantity)
            else
                currentQuantity
        }

        getTotals()

    }

    fun onClickAddOrder() {

        if (_products.value.isNotEmpty()) {

            val name = "huhuniu"
            val dateDelviry = "23/223/23"
            val dateActual = "343/43/43"

            val orderProducts = _products.value.map {
                OrderProducts(
                    idp = it.idp.toString(),
                    name = it.name,
                    priceMn = it.priceMN.toString(),
                    priceUsd = it.priceUSD.toString(),
                    image = it.image,
                    unit = _quantity.value[it.idp].toString(),
                )
            }


            val myOrder = Order(
                name = name,
                dateOrder = dateActual,
                dateDelivery = dateDelviry,
                listOrderProducts = orderProducts
            )


            viewModelScope.launch {


                addOrderUseCase(myOrder)
                deletCartUseCase.deleteCart()

            }
        }

    }

    fun changerDateDelivery(date: Long){
        _dateDelivery.value = date
    }


}

fun convertMillisToDate(millis: Long): String {
    val daysInMonth = intArrayOf(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    var days = millis / 86400000 // Milisegundos en un día
    var year = 1970
    while (true) {
        val daysInYear = if (isLeapYear(year)) 366 else 365
        if (days >= daysInYear) {
            days -= daysInYear
            year++
        } else {
            break
        }
    }
    var month = 1
    while (true) {
        val daysInCurrentMonth = if (month == 2 && isLeapYear(year)) 29 else daysInMonth[month]
        if (days >= daysInCurrentMonth) {
            days -= daysInCurrentMonth
            month++
        } else {
            break
        }
    }
    val day = days + 1
    return "${day.toString().padStart(2, '0')}/${month.toString().padStart(2, '0')}/$year"
}

fun isLeapYear(year: Int): Boolean {
    return year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)
}



fun Float.roundToDecimals(decimals: Int): Float {
    val multiplier = 10.0f.pow(decimals)
    return round(this * multiplier) / multiplier
}