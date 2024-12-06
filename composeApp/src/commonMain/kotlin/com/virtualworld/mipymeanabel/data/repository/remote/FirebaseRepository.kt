package com.virtualworld.mipymeanabel.data.repository.remote

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface FirebaseRepository {

    fun getAllProducts(): Flow<NetworkResponseState<List<Product>>>

}