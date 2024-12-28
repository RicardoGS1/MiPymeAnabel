package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.domain.models.AuthModel
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.flow.Flow

class FirebaseAuthDataSourceImpl {

    private val auth = Firebase.auth

    suspend fun signUp(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
    }

    suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
    }

    suspend fun signOut(){
        println("sale")
        auth.signOut()


    }

    fun loadUser(): Flow<FirebaseUser?> {


        return auth.authStateChanged

    }

    suspend fun reLoadUser() {

        auth.currentUser?.reload()



    }


}