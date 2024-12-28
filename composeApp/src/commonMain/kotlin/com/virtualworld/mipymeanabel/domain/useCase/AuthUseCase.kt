package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.AuthenticationState
import com.virtualworld.mipymeanabel.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthUseCase (private val authRepository: AuthRepository) {

    suspend fun singUp(email: String, password:String){
        authRepository.signUp (email, password)

    }

    suspend fun signIn(email: String, password:String){
        authRepository.signIn (email, password)

    }

    suspend fun singOut(){
        authRepository.signOut ()

    }

    fun loadUser() : Flow<AuthenticationState> {
        return authRepository.loadUser ()

    }



}