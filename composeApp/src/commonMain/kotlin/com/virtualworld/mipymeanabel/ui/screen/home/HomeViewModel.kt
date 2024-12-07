package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.domain.AddFavoriteUseCase
import com.virtualworld.mipymeanabel.domain.GetAllProductUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getAllProductUseCase: GetAllProductUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase
) : ViewModel() {

    private val _allProducts = MutableStateFlow<List<ProductAll>>(emptyList())
    val productsState: StateFlow<List<ProductAll>> get() = filteredProductsState()

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


            getAllProductUseCase().collect { listProducts ->

                when (listProducts) {
                    is NetworkResponseState.Error -> TODO()
                    NetworkResponseState.Loading -> TODO()
                    is NetworkResponseState.Success -> {

                        _allProducts.update { listProducts.result }
                        _categoryState.update {
                            listOf("Todos") + _allProducts.value.map { it.category }.distinct()
                        }
                        println("kkk3"+_allProducts.value)

                    }

                }
            }

        }



    }

    private fun filteredProductsState(): StateFlow<List<ProductAll>> =
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

    fun onClickFavorite(id: String) {

        viewModelScope.launch {
            addFavoriteUseCase.addFavorite(id.toLong())

        }

    }


}

