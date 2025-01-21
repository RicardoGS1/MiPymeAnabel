package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseAuthDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl (private val firebaseDataSource: FirebaseDataSource, private val firebaseAuthDataSource: FirebaseAuthDataSource) : OrderRepository {

    override suspend fun addOrder(order: Order, uid: String): NetworkResponseState<Boolean> {
        return firebaseDataSource.addOrder(order,uid)
    }

    override suspend fun getOrder(uid: String): Flow<NetworkResponseState<List<Order>>> {



        return firebaseDataSource.getOrders(uid)
    }
}