package com.virtualworld.mipymeanabel.ui.screen.detail

import androidx.lifecycle.ViewModel

class DetailViewModel( private val productId : String ) : ViewModel() {

    init {
        println(productId)
    }

    fun a (){
        println("jejeabajo")
    }


}