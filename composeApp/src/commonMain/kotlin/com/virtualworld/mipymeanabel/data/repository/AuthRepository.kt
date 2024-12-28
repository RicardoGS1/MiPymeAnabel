package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.AuthenticationState
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseAuthDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class AuthRepository(private val firebaseAuthDataSource: FirebaseAuthDataSourceImpl) {

    suspend fun signUp(email: String, password: String) {
        firebaseAuthDataSource.signUp(email, password)
    }

    suspend fun signIn(email: String, password: String) {
        firebaseAuthDataSource.signIn(email, password)
    }

    suspend fun signOut() {
        firebaseAuthDataSource.signOut()

    }

    fun loadUser(): Flow<AuthenticationState> {


        return firebaseAuthDataSource.loadUser().map { fireUser ->



            try {
                if (fireUser == null) {
                    AuthenticationState.Unauthenticated


                } else {
                    AuthenticationState.Authenticated(fireUser.email.toString())
                }
            } catch (e: Throwable) {
                AuthenticationState.AuthenticationError(e.message.toString())
            }

        }
    }


}

