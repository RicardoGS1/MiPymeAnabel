package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.repository.ProductRepository

class DeletCartUseCase (private val productRepository: ProductRepository) {

    suspend fun deleteCart (){
        productRepository.deleteAllCart()
    }

}