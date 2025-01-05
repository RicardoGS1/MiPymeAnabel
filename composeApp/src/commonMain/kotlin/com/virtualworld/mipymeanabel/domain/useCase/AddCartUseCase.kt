package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.repository.ProductRepository

class AddCartUseCase(private val productRepository: ProductRepository) {

    suspend fun addCart(id: Long) {
        productRepository.changerCart(id)
    }

}