package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.data.model.SignResponseState
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseAuthDataSource
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthRepository(private val firebaseAuthDataSource: FirebaseAuthDataSource) {

    suspend fun signUp(email: String, password: String, name: String) : SignResponseState {

       return try {
           firebaseAuthDataSource.signUp(email, password, name)
           SignResponseState.Success(name)
       } catch (e: Exception){
           SignResponseState.Error(e)
       }
    }

    suspend fun signIn(email: String, password: String): SignResponseState {


        return try {
            firebaseAuthDataSource.signIn(email, password)
            SignResponseState.Success(email)
        } catch (e: Exception) {
            SignResponseState.Error(e)
        }


    }

    suspend fun signOut() {
        firebaseAuthDataSource.signOut()

    }

    fun loadUser(): Flow<AuthenticationState<FirebaseUser>> {


        return firebaseAuthDataSource.loadUser().map { fireUser ->


            try {
                if (fireUser == null) {
                    AuthenticationState.Unauthenticated


                } else {
                    AuthenticationState.Authenticated(fireUser)
                }
            } catch (e: Throwable) {
                println("errrror en repoaut")
                AuthenticationState.AuthenticationError(e.message.toString())
            }

        }
    }

    fun getUid() : String {
        return firebaseAuthDataSource.getUid()
    }


}

