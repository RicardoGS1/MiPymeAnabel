package com.virtualworld.mipymeanabel.domain.useCase.auth

import com.virtualworld.mipymeanabel.data.repository.AuthRepository

class SignInUseCase(private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password:String){
        authRepository.signIn (email, password)

    }


}