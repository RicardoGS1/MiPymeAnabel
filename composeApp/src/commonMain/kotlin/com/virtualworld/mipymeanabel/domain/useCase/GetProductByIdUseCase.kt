package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.domain.models.ProductAll
import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductByIdUseCase(private val productRepository: ProductRepository) {
    operator fun invoke(productId: String): Flow<NetworkResponseState<ProductAll>> {

       val a = productRepository.getProductById(productId)
        return a
    }

}