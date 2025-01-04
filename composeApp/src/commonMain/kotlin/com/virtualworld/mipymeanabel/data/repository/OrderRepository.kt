package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface OrderRepository {


    fun addOrder(order: Order): NetworkResponseState<Boolean>

    fun getOrder (): Flow<NetworkResponseState<Order>>


}