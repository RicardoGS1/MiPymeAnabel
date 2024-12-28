package com.virtualworld.mipymeanabel.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.domain.useCase.AddCartUseCase
import com.virtualworld.mipymeanabel.domain.useCase.AddFavoriteUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetProductByIdUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel(private val productId : String, private val getProductByIdUseCase: GetProductByIdUseCase, private val addFavoriteUseCase: AddFavoriteUseCase, private val addCartUseCase: AddCartUseCase) : ViewModel() {


    private val _productState = MutableStateFlow<ProductAll>(ProductAll())
    val productState: StateFlow<ProductAll> get() = _productState.asStateFlow()

    init {
        getProductById()
    }

    private fun getProductById() {

        viewModelScope.launch {

          getProductByIdUseCase(productId).collect{ prduct->

               when(prduct){
                   is NetworkResponseState.Error -> {}
                   NetworkResponseState.Loading -> {}
                   is NetworkResponseState.Success -> _productState.update {

                       prduct.result
                   }
               }


           }

        }

    }

    fun onClickFavorite(id: String) {

        viewModelScope.launch {
            addFavoriteUseCase.addFavorite(id.toLong())

        }

    }

    fun onClickAddCart(id: String) {

        viewModelScope.launch {
            addCartUseCase.addCart(id.toLong())

        }

    }

}