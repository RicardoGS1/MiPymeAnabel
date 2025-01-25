package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.repository.AuthRepository
import com.virtualworld.mipymeanabel.data.repository.OrderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetOrderByIdUseCase (private val orderRepository: OrderRepository, private val authRepository: AuthRepository)  {

    suspend operator fun invoke(orderId:String): Flow<NetworkResponseState<Order>>{

        val uid = authRepository.getUid()

       return orderRepository.getOrderById(uid,orderId).map {
          when(it){
              is NetworkResponseState.Error -> NetworkResponseState.Error(it.exception)
              is NetworkResponseState.Loading -> NetworkResponseState.Loading
              is NetworkResponseState.Success -> NetworkResponseState.Success(it.result)

          }
       }
    }
}