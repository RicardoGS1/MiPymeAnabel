package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import com.virtualworld.mipymeanabel.domain.useCase.GetProductCartUseCase
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import com.virtualworld.mipymeanabel.domain.useCase.AddOrderUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.pow

class CartViewModel(private val getProductCartUseCase: GetProductCartUseCase, private val addOrderUseCase: AddOrderUseCase) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductCart>>(emptyList())
    val productsState: StateFlow<List<ProductCart>> get() = _products

    private val _quantity = MutableStateFlow<Map<Long, Int>>(emptyMap())
    val quantity: StateFlow<Map<Long, Int>> get() = _quantity

    private val _totals = MutableStateFlow<Map<String, Float>>(emptyMap())
    val totals: StateFlow<Map<String, Float>> get() = _totals

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

        _products.value.forEach { prod->

            val priceUSD = prod.priceUSD
            val priceMN = prod.priceMN

            totalUSD += priceUSD*_quantity.value[prod.idp]!!

            totalMN += priceMN*_quantity.value[prod.idp]!!

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

        val myOrderProduct1 = OrderProducts(
            idp = "2",
            name = "koko",
            priceMn = "89",
            priceUsd = "90",
            detail = "jk",
            available = "ko",
            image = "mkim",
            category = "ko",
            unit = "2",
        )
        val myOrderProduct2 = OrderProducts(
            idp = "3",
            name = "ko66666ko",
            priceMn = "89",
            priceUsd = "90",
            detail = "jk",
            available = "ko",
            image = "mkim",
            category = "ko",
            unit = "2",
        )

        val myOrderProduct3 = OrderProducts(
            idp = "4",
            name = "mkmkkoko",
            priceMn = "89",
            priceUsd = "90",
            detail = "jk",
            available = "ko",
            image = "mkim",
            category = "ko",
            unit = "2",
        )

        val myListOrders: List<OrderProducts> =
            listOf(myOrderProduct1, myOrderProduct2, myOrderProduct3)

        val myOrder = Order(
            name = "culetef",
            dateOrder = "23/67/890",
            dateDelivery = "56/78/89",
            listOrderProducts = myListOrders
        )

        viewModelScope.launch {

        addOrderUseCase(myOrder)
        }

    }


}


fun Float.roundToDecimals(decimals: Int): Float {
    val multiplier = 10.0f.pow(decimals)
    return kotlin.math.round(this * multiplier) / multiplier
}