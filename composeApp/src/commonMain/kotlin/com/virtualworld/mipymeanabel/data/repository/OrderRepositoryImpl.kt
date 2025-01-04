package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl : OrderRepository {

    override fun addOrder(order: Order): NetworkResponseState<Boolean> {
        TODO("Not yet implemented")
    }

    override fun getOrder(): Flow<NetworkResponseState<Order>> {
        TODO("Not yet implemented")
    }
}