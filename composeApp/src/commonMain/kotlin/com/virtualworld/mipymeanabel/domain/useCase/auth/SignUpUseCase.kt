package com.virtualworld.mipymeanabel.domain.useCase.auth

import com.virtualworld.mipymeanabel.data.repository.AuthRepository

class SignUpUseCase (private val authRepository: AuthRepository) {

    suspend operator fun invoke(email: String, password:String){
        authRepository.signUp (email, password)

    }
}