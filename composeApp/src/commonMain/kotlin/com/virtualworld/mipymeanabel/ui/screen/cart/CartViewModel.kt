package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.domain.GetProductCartUseCase
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(private val getProductCartUseCase: GetProductCartUseCase) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductCart>>(emptyList())
    val productsState: StateFlow<List<ProductCart>> get() = _products

    private val _quantity = MutableStateFlow<Map<Long, Int>>(emptyMap())
    val quantity: StateFlow<Map<Long, Int>> get() = _quantity

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

            }


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

    }


}