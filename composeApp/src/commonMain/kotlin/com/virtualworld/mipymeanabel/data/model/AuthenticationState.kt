package com.virtualworld.mipymeanabel.data.model

import dev.gitlive.firebase.auth.FirebaseUser

sealed class AuthenticationState <out T : Any> {
    object Unauthenticated : AuthenticationState<Nothing>()
    object Loading : AuthenticationState<Nothing>()
    data class Authenticated<out T : Any>(val result: T) : AuthenticationState<T>()
    data class AuthenticationError(val error: String) : AuthenticationState<Nothing>()
}