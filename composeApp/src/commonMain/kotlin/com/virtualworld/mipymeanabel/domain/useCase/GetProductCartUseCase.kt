package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import kotlinx.coroutines.flow.Flow

class GetProductCartUseCase(private val productRepository: ProductRepository) {

    operator fun invoke(): Flow<List<ProductCart>> {
       return productRepository.getProductCart()
    }



}