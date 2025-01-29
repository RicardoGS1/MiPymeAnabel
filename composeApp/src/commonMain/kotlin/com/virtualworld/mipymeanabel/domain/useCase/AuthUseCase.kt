package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.data.model.SignResponseState
import com.virtualworld.mipymeanabel.data.repository.AuthRepository
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class AuthUseCase (private val authRepository: AuthRepository) {

    suspend fun singUp(email: String, password: String, name: String) : SignResponseState{
        return authRepository.signUp (email, password, name)

    }

    suspend fun signIn(email: String, password:String) : SignResponseState {
       return authRepository.signIn (email, password)

    }

    suspend fun singOut(){
        authRepository.signOut ()

    }

    fun loadUser() : Flow<AuthenticationState<FirebaseUser>> {
        return authRepository.loadUser ()

    }



}