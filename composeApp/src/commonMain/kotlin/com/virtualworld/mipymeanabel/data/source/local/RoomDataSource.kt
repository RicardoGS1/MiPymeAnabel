package com.virtualworld.mipymeanabel.data.source.local

import com.virtualworld.mipymeanabel.data.dto.ProductInfo
import kotlinx.coroutines.flow.Flow

interface RoomDataSource {

    fun getInfoProducts(): Flow<List<ProductInfo>>

    fun getInfoProductById(id: Long): Flow<ProductInfo?>

    suspend fun updateInfoProduct(productInfo: ProductInfo)

    suspend fun deleteAllCart()

}