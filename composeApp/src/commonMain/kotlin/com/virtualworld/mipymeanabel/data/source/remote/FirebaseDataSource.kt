package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface FirebaseDataSource {

    fun getAllProducts() : Flow<NetworkResponseState<List<Product>>>

    suspend fun getProductById(productId: String): NetworkResponseState<Product>
}