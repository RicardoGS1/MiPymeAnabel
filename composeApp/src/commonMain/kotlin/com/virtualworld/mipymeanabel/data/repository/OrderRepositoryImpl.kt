package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseAuthDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.data.utils.generateRandomCode
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.flow.Flow

class OrderRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource,
    private val firebaseAuthDataSource: FirebaseAuthDataSource
) : OrderRepository {

    override suspend fun addOrder(order: Order, uid: String): NetworkResponseState<Boolean> {

        val completedOrder =

            order.copy(number = generateRandomCode(), dateOrder = GMTDate().timestamp.toString())

        return firebaseDataSource.addOrder(completedOrder, uid)
    }

    override suspend fun getOrder(uid: String): Flow<NetworkResponseState<List<Order>>> {


        return firebaseDataSource.getOrders(uid)
    }
}