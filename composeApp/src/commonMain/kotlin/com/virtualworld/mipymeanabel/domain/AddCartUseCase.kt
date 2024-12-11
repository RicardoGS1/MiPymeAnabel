package com.virtualworld.mipymeanabel.domain

import com.virtualworld.mipymeanabel.data.repository.ProductRepository

class AddCartUseCase(private val productRepository: ProductRepository) {

    suspend fun addCart(id: Long) {
        productRepository.changerCart(id)
    }

}