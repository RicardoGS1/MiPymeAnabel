package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.domain.GetProductCartUseCase
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CartViewModel(private val getProductCartUseCase: GetProductCartUseCase) : ViewModel() {

    private val _products = MutableStateFlow<List<ProductCart>>(emptyList())
    val productsState : StateFlow<List<ProductCart>> get() = _products

    init {
        getProductsCart()
    }

    private fun getProductsCart() {

        viewModelScope.launch {

        getProductCartUseCase().collect { products ->

            _products.update {
                products
            }

        }


        }

    }

}