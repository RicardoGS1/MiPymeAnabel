package com.virtualworld.mipymeanabel.domain

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import com.virtualworld.mipymeanabel.data.repository.remote.FirebaseRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllProductUseCase(private val firebaseRepository: FirebaseRepository) {


    operator fun invoke(): Flow<NetworkResponseState<List<Product>>> {

        return firebaseRepository.getAllProducts().map { products ->

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