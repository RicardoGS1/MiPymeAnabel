package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun changerCart(id:Long)

   suspend fun changerFavorite(id:Long)

    fun getAllProducts(): Flow<NetworkResponseState<List<ProductAll>>>

    suspend fun getProductById(productId: String): NetworkResponseState<ProductAll>


}