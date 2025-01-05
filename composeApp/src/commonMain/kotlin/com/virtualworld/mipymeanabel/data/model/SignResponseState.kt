package com.virtualworld.mipymeanabel.data.model

sealed class SignResponseState {

    data object Idle : SignResponseState()
    data object Loading : SignResponseState()
    data class Success(val mail: String) : SignResponseState()
    data class Error(val exception: Exception) : SignResponseState()

}
