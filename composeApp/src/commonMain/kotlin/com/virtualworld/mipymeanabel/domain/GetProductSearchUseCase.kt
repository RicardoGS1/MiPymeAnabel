package com.virtualworld.mipymeanabel.domain

import coil3.network.NetworkResponse
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map

class GetProductSearchUseCase(private val firebaseDataSourceImpl: FirebaseDataSourceImpl) {

    operator fun invoke(searchText: String): Flow<NetworkResponseState<List<Product>>> {

        return firebaseDataSourceImpl.getProducts().map { products ->

            when (products) {
                is NetworkResponseState.Success -> {

                    val productsSearch = products.result.filter { product ->
                        product.name.contains(searchText, ignoreCase = true)
                    }

                    NetworkResponseState.Success(productsSearch)

                }

                is NetworkResponseState.Error -> NetworkResponseState.Error(products.exception)
                else -> NetworkResponseState.Loading
            }

        }
    }
}