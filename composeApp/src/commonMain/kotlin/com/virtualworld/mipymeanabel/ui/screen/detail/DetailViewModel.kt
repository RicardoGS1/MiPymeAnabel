package com.virtualworld.mipymeanabel.ui.screen.detail

import androidx.lifecycle.ViewModel
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow

class DetailViewModel( private val productId : String ) : ViewModel() {

    val productState : StateFlow<ProductAll> = MutableStateFlow<ProductAll>(ProductAll(
        idp= 10.toString(),
        name="Rollo de mantel AA-3226A",
        priceMn= 10.toString(),
        priceUsd= 92.toString(),
        detail="Medida: 1.37 m x 20 m",
        available= 1.toString(),
        image="https://drive.usercontent.google.com/download?id=1fcG6jJ8egGqHgUklr-tBNLqTDXB8JaY5&export=download&confirm=t&uuid=4edb6c5d-3af2-4d99-b6e2-973a307f11ae",
        category="Cocina",
        favorite = true,
        cart = true
    )).asStateFlow()

    init {
        println(productId)
    }

    fun a (){
        println("jejeabajo")
    }


}