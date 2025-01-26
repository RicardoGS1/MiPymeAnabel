package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.domain.models.ProductAll
import com.virtualworld.mipymeanabel.domain.useCase.AddFavoriteUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetAllProductUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetBanelUseCase
import com.virtualworld.mipymeanabel.ui.screen.common.model.ScreenStates
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
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val getBanelUseCase: GetBanelUseCase
) : ViewModel() {

//    private val _screenState = MutableStateFlow<ScreenStates> (ScreenStates.Loading)
//    val screenState: StateFlow<ScreenStates> get() = _screenState

    private val _allProducts = MutableStateFlow<ScreenStates<List<ProductAll>>>(ScreenStates.Loading)
    val productsState: StateFlow<ScreenStates<List<ProductAll>>> get() = filteredProductsState()

    private val _allBanel = MutableStateFlow<ScreenStates<List<String>>>(ScreenStates.Loading)
    val allBanel: StateFlow<ScreenStates<List<String>>> get() = _allBanel

    private val _categoryState = MutableStateFlow<List<String>>(emptyList())
    val categoryState: StateFlow<List<String>> get() = _categoryState.asStateFlow()

    private val _selectedCategoryState = MutableStateFlow<String>("Todos")
    val selectedCategoryState: StateFlow<String> get() = _selectedCategoryState.asStateFlow()


    private val _searchText = MutableStateFlow<String>("")
    val searchText: StateFlow<String> get() = _searchText.asStateFlow()


    init {
        getAllProducts()
        getAllBanel()

    }

    private fun getAllBanel() {
        viewModelScope.launch {

            getBanelUseCase().collect { banel->

                when(banel){
                    is NetworkResponseState.Error -> { _allBanel.value = ScreenStates.Error(banel.exception.message.toString())  }
                    NetworkResponseState.Loading -> { _allBanel.value = ScreenStates.Loading }
                    is NetworkResponseState.Success -> {
                        println(banel.result)
                        _allBanel.value = ScreenStates.Success(banel.result)
                    }
                }
            }

        }
    }

    private fun getAllProducts() {

        viewModelScope.launch {


            getAllProductUseCase().collect { listProducts ->

                when (listProducts) {
                    is NetworkResponseState.Error -> { _allProducts.value = ScreenStates.Error(listProducts.exception.message.toString())    }
                    NetworkResponseState.Loading -> {_allProducts.value = ScreenStates.Loading  }
                    is NetworkResponseState.Success -> {

                        _allProducts.update { ScreenStates.Success(listProducts.result) }

                        _categoryState.update {
                            listOf("Todos", "Favorites") + listProducts.result.map { it.category }
                                .distinct()
                        }

                    }

                }
            }

        }


    }

    private fun filteredProductsState(): StateFlow<ScreenStates<List<ProductAll>>> =
        combine(
            _searchText,
            _selectedCategoryState,
            _allProducts
        ) { text, category, listAllProducts ->


            when (listAllProducts) {
                is ScreenStates.Success -> {
                    val filteredProducts = listAllProducts.result.filter { product ->
                        product.name.contains(text, ignoreCase = true) &&
                                (product.category == category || category == "Todos" || (category == "Favorites" && product.favorite))
                    }
                    ScreenStates.Success(filteredProducts)
                }
                is ScreenStates.Error -> ScreenStates.Error(listAllProducts.exception)
                is ScreenStates.Loading -> ScreenStates.Loading
            }
//
//            if( listAllProducts as ScreenStates.Success ){
//
//            }
//
//            listAllProducts .filter { product ->
//                product.name.contains(text, ignoreCase = true) &&
//                        (product.category == category || category == "Todos" || (category == "Favorites" && product.favorite))
//            }

        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(),
            _allProducts.value
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

    fun onClickProduct(id: String) {

    }


}

