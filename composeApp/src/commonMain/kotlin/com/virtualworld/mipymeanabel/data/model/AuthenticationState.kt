package com.virtualworld.mipymeanabel.data.model

sealed class AuthenticationState {
    object Unauthenticated : AuthenticationState()
    object Loading : AuthenticationState()
    data class Authenticated(val user: String) : AuthenticationState()
    data class AuthenticationError(val error: String) : AuthenticationState()
}