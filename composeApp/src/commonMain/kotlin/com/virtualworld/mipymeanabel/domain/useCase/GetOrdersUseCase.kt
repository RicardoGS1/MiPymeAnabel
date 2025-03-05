package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase (private val orderRepository: OrderRepository) {

    suspend operator fun invoke(uid: String): Flow<NetworkResponseState<List<Order>>>{
       return orderRepository.getOrder(uid)
    }


}