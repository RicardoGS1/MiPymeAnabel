package com.virtualworld.mipymeanabel.domain

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetProductSearchUseCase(private val firebaseDataSourceImpl: FirebaseDataSourceImpl) {

    operator fun invoke(): Flow<NetworkResponseState<List<Product>>> {

        return firebaseDataSourceImpl.getProducts().map { products ->

            when (products) {
                is NetworkResponseState.Success -> {
                    NetworkResponseState.Success(products.result)
                }

                is NetworkResponseState.Error -> NetworkResponseState.Error(products.exception)
                else -> NetworkResponseState.Loading
            }

        }
    }
}