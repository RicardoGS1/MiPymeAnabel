package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import kotlinx.coroutines.flow.Flow

interface FirebaseDataSource {

    fun getAllProducts() : Flow<NetworkResponseState<List<Product>>>

    suspend fun getProductById(productId: String): NetworkResponseState<Product>

    suspend fun addOrder(order: Order, uid: String) : NetworkResponseState<Boolean>

    fun getOrders(uid: String?): Flow<NetworkResponseState<List<Order>>>

    fun getAllBanels() : Flow<NetworkResponseState<List<String>>>

    fun getOrderById(uid: String, orderId: String) : Flow<NetworkResponseState<Order>>
}