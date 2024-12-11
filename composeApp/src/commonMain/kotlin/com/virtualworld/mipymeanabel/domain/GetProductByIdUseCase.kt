package com.virtualworld.mipymeanabel.domain

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductByIdUseCase(private val productRepository: ProductRepository) {
    operator fun invoke(productId: String): Flow<NetworkResponseState<ProductAll>> {

       val a = productRepository.getProductById(productId)
        return a
    }

}