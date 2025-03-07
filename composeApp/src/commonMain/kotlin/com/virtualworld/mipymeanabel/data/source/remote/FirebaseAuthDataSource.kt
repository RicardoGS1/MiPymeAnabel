package com.virtualworld.mipymeanabel.data.source.remote

import dev.gitlive.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface FirebaseAuthDataSource {

    suspend fun signUp(email: String, password: String, name: String)

    suspend fun signIn(email: String, password: String)

    suspend fun signOut()

    fun loadUser(): Flow<FirebaseUser?>

    fun getUid() : String

    suspend fun reLoadUser()
}