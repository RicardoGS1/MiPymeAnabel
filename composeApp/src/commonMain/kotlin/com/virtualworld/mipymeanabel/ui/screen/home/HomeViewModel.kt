package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.model.Product
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val firebaseDataSourceImpl: FirebaseDataSourceImpl): ViewModel() {

    private val _productsState = MutableStateFlow<List<Product>>(emptyList())
    val productsState: StateFlow<List<Product>> get() = _productsState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {

        viewModelScope.launch {

            firebaseDataSourceImpl.getUsers().collect { listProducts ->

                _productsState.update {
                    listProducts
                }
                println(listProducts)

            }

        }
    }

}

