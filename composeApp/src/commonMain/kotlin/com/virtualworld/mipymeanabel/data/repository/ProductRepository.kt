package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun changerCart(id:Long)

   suspend fun changerFavorite(id:Long)

    fun getAllProducts(): Flow<NetworkResponseState<List<ProductAll>>>

    fun getProductById(productId: String): Flow<NetworkResponseState<ProductAll>>

    fun getProductCart(): Flow<List<ProductCart>>

    suspend fun deleteAllCart()


}