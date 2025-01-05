package com.virtualworld.mipymeanabel.data.source.remote

import dev.gitlive.firebase.auth.FirebaseAuth
import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

class FirebaseAuthDataSourceImpl(private val auth: FirebaseAuth) : FirebaseAuthDataSource {


    override suspend fun signUp(email: String, password: String, name: String) {
        auth.createUserWithEmailAndPassword(email, password)

        auth.currentUser?.updateProfile(displayName =  name)

    }

    override suspend fun signIn(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
       // auth.currentUser?.displayName
    }

    override suspend fun signOut() {
        auth.signOut()
    }

    override fun loadUser(): Flow<FirebaseUser?> {

        return auth.authStateChanged

    }

    override suspend fun reLoadUser() {

        auth.currentUser?.reload()


    }


}