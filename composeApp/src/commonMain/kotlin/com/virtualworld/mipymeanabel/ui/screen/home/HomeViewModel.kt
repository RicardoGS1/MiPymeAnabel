package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import com.virtualworld.mipymeanabel.domain.GetProductSearchUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.text.contains

class HomeViewModel(
    private val getProductSearchUseCase: GetProductSearchUseCase
) : ViewModel() {

    private val _allProducts = MutableStateFlow<List<Product>>(emptyList())
    val productsState: StateFlow<List<Product>> get() = filteredProductsState()

    private val _categoryState = MutableStateFlow<List<String>>(emptyList())
    val categoryState: StateFlow<List<String>> get() = _categoryState.asStateFlow()

    private val _selectedCategoryState = MutableStateFlow<String>("Todos")
    val selectedCategoryState: StateFlow<String> get() = _selectedCategoryState.asStateFlow()


    private val _searchText = MutableStateFlow<String>("")
    val searchText: StateFlow<String> get() = _searchText.asStateFlow()


    init {
        getAllProducts()
    }

    private fun getAllProducts() {

        viewModelScope.launch {

            getProductSearchUseCase().collect { listProducts ->

                when (listProducts) {
                    is NetworkResponseState.Error -> TODO()
                    NetworkResponseState.Loading -> TODO()
                    is NetworkResponseState.Success -> {

                        _allProducts.update { listProducts.result }
                        _categoryState.update {
                            listOf("Todos") + _allProducts.value.map { it.category }.distinct()
                        }

                    }

                }
            }

        }

    }

    private fun filteredProductsState(): StateFlow<List<Product>> =
        combine(searchText, selectedCategoryState, _allProducts) { text, category, listAllProducts ->

            listAllProducts.filter { product ->
                product.name.contains(text, ignoreCase = true) &&
                        (product.category == category || category == "Todos")
            }

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(), _allProducts.value
        )


    fun updateSearchText(searchText: String) {
        _searchText.update { searchText }
    }


    fun updateSelectedCategory(selected: String) {
        _selectedCategoryState.update {
            selected
        }
    }


}

