package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import kotlinx.coroutines.flow.Flow

interface FirebaseDataSource {

    fun getAllProducts() : Flow<NetworkResponseState<List<Product>>>
}