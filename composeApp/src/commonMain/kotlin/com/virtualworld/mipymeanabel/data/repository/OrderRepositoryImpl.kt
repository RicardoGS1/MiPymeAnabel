package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import com.virtualworld.mipymeanabel.data.mapper.toOrderDetail
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.data.utils.generateRandomCode
import com.virtualworld.mipymeanabel.domain.models.OrderDetail
import io.ktor.util.date.GMTDate
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class OrderRepositoryImpl(
    private val firebaseDataSource: FirebaseDataSource,
) : OrderRepository {


    override suspend fun addDocumentOrder(
        uid: String,
        token: String
    ): NetworkResponseState<Boolean> {
      return  firebaseDataSource.addDocumentOrder(uid, token)
    }

    override suspend fun addOrder(order: Order, uid: String): NetworkResponseState<Boolean> {

        val completedOrder =

            order.copy(number = generateRandomCode(), dateOrder = GMTDate().timestamp.toString())

        return firebaseDataSource.addOrder(completedOrder, uid)
    }

    override suspend fun getOrder(uid: String): Flow<NetworkResponseState<List<Order>>> {


        return firebaseDataSource.getOrders(uid)
    }

    override suspend fun getOrderById(
        uid: String,
        orderId: String
    ): Flow<NetworkResponseState<OrderDetail>> {


        return firebaseDataSource.getOrderById(uid, orderId).map {

            when (it) {
                is NetworkResponseState.Error -> {
                    NetworkResponseState.Error(it.exception)
                }

                is NetworkResponseState.Loading -> {
                    NetworkResponseState.Loading
                }

                is NetworkResponseState.Success -> {
                    NetworkResponseState.Success(it.result.toOrderDetail())
                }
            }


        }
    }
}

