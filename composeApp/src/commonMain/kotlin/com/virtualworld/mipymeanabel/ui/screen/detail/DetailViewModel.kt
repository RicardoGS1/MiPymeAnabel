package com.virtualworld.mipymeanabel.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.domain.GetProductByIdUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel( private val productId : String, private val getProductByIdUseCase: GetProductByIdUseCase ) : ViewModel() {


    private val _productState = MutableStateFlow<ProductAll>(ProductAll())
    val productState: StateFlow<ProductAll> get() = _productState

    init {
        getProductById()
    }

    private fun getProductById() {

        viewModelScope.launch {

           val result = getProductByIdUseCase(productId)

            when(result){
                is NetworkResponseState.Error -> TODO()
                NetworkResponseState.Loading -> TODO()
                is NetworkResponseState.Success -> _productState.update {
                    it
                }
            }

        }

    }


}