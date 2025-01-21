package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import kotlinx.coroutines.flow.Flow

interface OrderRepository {


    suspend fun addOrder(order: Order, uid: String): NetworkResponseState<Boolean>

    suspend fun getOrder(uid: String): Flow<NetworkResponseState<List<Order>>>


}