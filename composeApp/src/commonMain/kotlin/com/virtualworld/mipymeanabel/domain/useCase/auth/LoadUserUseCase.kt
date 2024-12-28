package com.virtualworld.mipymeanabel.domain.useCase.auth

import com.virtualworld.mipymeanabel.data.AuthenticationState
import com.virtualworld.mipymeanabel.data.repository.AuthRepository
import kotlinx.coroutines.flow.Flow

class LoadUserUseCase (private val authRepository: AuthRepository) {

    fun invoke() : Flow<AuthenticationState> {
       return authRepository.loadUser ()

    }
}