package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.domain.GetProductCartUseCase
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.pow

class CartViewModel(private val getProductCartUseCase: GetProductCartUseCase) : ViewModel() {

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

                if (_quantity.value.isEmpty())
                    _quantity.update {
                        products.associate { it.idp to 1 }
                    }
                getTotals()

            }


        }

    }

    fun getTotals() {

        var totalUSD = 0f
        var totalMN = 0f
        var units = 0f

        _quantity.value.forEach { quant ->

            val priceUSD = _products.value.first { quant.key == it.idp }.priceUSD

            val priceMN = _products.value.first { quant.key == it.idp }.priceMN

            totalUSD += priceUSD * quant.value

            totalMN += priceMN * quant.value

            units += quant.value


        }

        _totals.update {
            mapOf("totalUSD" to totalUSD.roundToDecimals(2))  +  mapOf("totalMN" to totalMN.roundToDecimals(2))  +  mapOf("units" to units)

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


}


fun Float.roundToDecimals(decimals: Int): Float {
    val multiplier = 10.0f.pow(decimals) // Usar Float para el multiplicador
    return kotlin.math.round(this * multiplier) / multiplier
}