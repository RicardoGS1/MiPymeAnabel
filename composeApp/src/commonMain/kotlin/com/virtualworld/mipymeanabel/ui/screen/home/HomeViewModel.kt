package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(private val firebaseDataSourceImpl: FirebaseDataSourceImpl): ViewModel() {

    private val _productsState = MutableStateFlow<List<Products>>(emptyList())
    val productsState: StateFlow<List<Products>> get() = _productsState.asStateFlow()

    init {
        getProducts()
    }

    private fun getProducts() {

viewModelScope.launch {
    firebaseDataSourceImpl.getUsers().collect {
        println(it)

    }

}


        _productsState.update {
            listOf(
                Products(0,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(1,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(2,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(3,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(4,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(5,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(6,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(7,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(8,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0),
                Products(9,"jjjj","mkmkmk","kokok","https://picsum.photos/id/10/200/300",9.0)
            )
        }
    }


}




data class Products (
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val imageUrl: String,
    val rating: Double,
)