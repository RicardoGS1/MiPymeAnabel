package com.virtualworld.mipymeanabel.ui.screen.model


sealed class ScreenStates <out T : Any> {

    object Loading : ScreenStates<Nothing>()

    data class Success<out T : Any>(val result: T) : ScreenStates<T>()

    data class Error(val exception: String) : ScreenStates<Nothing>()



}