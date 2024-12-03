package com.virtualworld.mipymeanabel.data.repository.remote

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import kotlinx.coroutines.flow.Flow

class FirebaseRepositoryImp (private val firebaseDataSource: FirebaseDataSource) : FirebaseRepository  {

    override fun getAllProducts(): Flow<NetworkResponseState<List<Product>>> {
       return firebaseDataSource.getAllProducts()
    }


}