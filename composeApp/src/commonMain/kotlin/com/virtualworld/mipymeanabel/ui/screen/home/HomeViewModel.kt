package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import com.virtualworld.mipymeanabel.data.source.remote.Productss
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val firebaseDataSourceImpl: FirebaseDataSourceImpl): ViewModel() {

    private val _productsState = MutableStateFlow<List<Productss>>(emptyList())
    val productsState: StateFlow<List<Productss>> get() = _productsState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {

        viewModelScope.launch {
            firebaseDataSourceImpl.getUsers().collect { listProductss ->

                _productsState.update {
                    listProductss
                }
                println(listProductss)

            }

        }
    }

}

