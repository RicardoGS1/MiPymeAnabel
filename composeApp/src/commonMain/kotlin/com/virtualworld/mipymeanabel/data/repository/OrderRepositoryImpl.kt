package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
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

            when(it){
                is NetworkResponseState.Error -> {NetworkResponseState.Error(it.exception)}
                is NetworkResponseState.Loading -> {NetworkResponseState.Loading}
                is NetworkResponseState.Success -> {

                   val mapOrderDetail = OrderDetail(

                       number = it.result.number,
                       state = it.result.state,
                       dateOrder = it.result.dateOrder,
                       dateDelivery = it.result.dateDelivery,
                       importTotal = calculateTotalImport(it.result.listOrderProducts).toString(),
                       unitTotal =  calculateTotalUnit(it.result.listOrderProducts) .toString(),
                       listOrderProducts = it.result.listOrderProducts

                   )


                    NetworkResponseState.Success(mapOrderDetail)
                }
            }


        }
    }
}

fun calculateTotalUnit(listOrderProducts: List<OrderProducts>): Float {

    var totalUsd = 0.0f

    listOrderProducts.forEach { product ->
        totalUsd += product.unit.toInt()
    }
    return totalUsd
}

fun calculateTotalImport(listOrderProducts: List<OrderProducts>): Float {

    var totalUsd = 0.0f

    listOrderProducts.forEach { product ->
        val priceUsd = product.priceUsd.toFloatOrNull() ?: 0.0f
        totalUsd += priceUsd * product.unit.toInt()
    }
    return totalUsd
}