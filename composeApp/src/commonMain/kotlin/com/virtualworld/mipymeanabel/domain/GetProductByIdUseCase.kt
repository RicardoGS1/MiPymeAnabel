package com.virtualworld.mipymeanabel.domain

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import kotlinx.coroutines.delay

class GetProductByIdUseCase(private val productRepository: ProductRepository) {

   suspend operator fun invoke(productId: String): NetworkResponseState<ProductAll> {


       val a = productRepository.getProductById(productId)

       println(a)

       return a

    }


}