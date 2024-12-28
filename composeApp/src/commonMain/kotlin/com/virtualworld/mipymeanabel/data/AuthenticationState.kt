package com.virtualworld.mipymeanabel.data

import dev.gitlive.firebase.auth.FirebaseUser

sealed class AuthenticationState {
    object Unauthenticated : AuthenticationState()
    object Loading : AuthenticationState()
    data class Authenticated(val user: String) : AuthenticationState()
    data class AuthenticationError(val error: String) : AuthenticationState()
}