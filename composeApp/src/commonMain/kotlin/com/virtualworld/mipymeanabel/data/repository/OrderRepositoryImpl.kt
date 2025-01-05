package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl (private val firebaseDataSource: FirebaseDataSource) : OrderRepository {

    override suspend fun addOrder(order: Order): NetworkResponseState<Boolean> {
        return firebaseDataSource.addOrder(order)
    }

    override fun getOrder(): Flow<NetworkResponseState<List<Order>>> {
       return firebaseDataSource.getOrders()
    }
}