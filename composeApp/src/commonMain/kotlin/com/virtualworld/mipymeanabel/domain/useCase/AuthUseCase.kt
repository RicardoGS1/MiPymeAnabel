package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.data.model.SignResponseState
import com.virtualworld.mipymeanabel.data.notification.NotificationService
import com.virtualworld.mipymeanabel.data.repository.AuthRepository
import com.virtualworld.mipymeanabel.data.repository.OrderRepository
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow

class AuthUseCase(
    private val authRepository: AuthRepository,
    private val notificationService: NotificationService,
    private val orderRepository: OrderRepository
) {

    suspend fun singUp(email: String, password: String, name: String): SignResponseState {

        val resultAuth = authRepository.signUp(email, password, name)
        val resultToken = notificationService.getToken()
        val resultUid = authRepository.getUid()

        if (resultAuth is SignResponseState.Success) {
            orderRepository.addDocumentOrder(resultUid, resultToken!!)
        }

        return resultAuth

    }

    suspend fun signIn(email: String, password: String): SignResponseState {

        val resultAuth = authRepository.signIn(email, password)
        val resultToken = notificationService.getToken()
        val resultUid = authRepository.getUid()

        if (resultAuth is SignResponseState.Success) {
            orderRepository.addDocumentOrder(resultUid, resultToken!!)
        }

        return resultAuth

    }

    suspend fun singOut() {
        authRepository.signOut()

    }

    fun loadUser(): Flow<AuthenticationState<FirebaseUser>> {
        return authRepository.loadUser()

    }


}