package com.virtualworld.mipymeanabel.domain.useCase.auth

import com.virtualworld.mipymeanabel.data.repository.AuthRepository

class SignOutUseCase (private val authRepository: AuthRepository) {

    suspend operator fun invoke(){
        authRepository.signOut ()

    }
}