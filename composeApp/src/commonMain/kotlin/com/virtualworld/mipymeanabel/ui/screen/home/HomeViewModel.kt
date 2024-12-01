package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import com.virtualworld.mipymeanabel.domain.GetProductSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getProductSearchUseCase: GetProductSearchUseCase
) : ViewModel() {

    private val _productsState = MutableStateFlow<List<Product>>(emptyList())
    val productsState: StateFlow<List<Product>> get() = _productsState.asStateFlow()

    private var allProducts: List<Product> = emptyList()

    private val _searchText = MutableStateFlow<String>("")
    val searchText: StateFlow<String> get() = _searchText.asStateFlow()


    init {
        getAllProducts("")
    }

    fun updateSearchText(searchText: String) {
        _searchText.update { searchText }
        getProductsSearch()
    }

    private fun getProductsSearch() {

            // Filtrar la lista en memoria
            val filteredProducts = allProducts.filter { product ->
                product.name.contains(_searchText.value, ignoreCase = true)
            }
            _productsState.update { filteredProducts } // Actualizar el estado con la lista filtrada

    }

    private fun getAllProducts(searchText: String) {

        viewModelScope.launch {

            getProductSearchUseCase(searchText).collect { listProducts ->

                when (listProducts) {
                    is NetworkResponseState.Error -> TODO()
                    NetworkResponseState.Loading -> TODO()
                    is NetworkResponseState.Success -> {
                        allProducts = listProducts.result
                        getProductsSearch()
                    }

                }
            }

        }

    }

//    private fun getProducts() {
//
//        viewModelScope.launch {
//
//            firebaseDataSourceImpl.getProducts().collect { listProducts ->
//
//                _productsState.update {
//                   // listProducts
//                }
//                println(listProducts)
//
//            }
//
//        }
//    }

}

