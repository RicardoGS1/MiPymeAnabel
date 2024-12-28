package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetAllProductUseCase(private val productRepository: ProductRepository) {


    operator fun invoke(): Flow<NetworkResponseState<List<ProductAll>>> {

        return productRepository.getAllProducts().map { products ->

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