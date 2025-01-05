package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase (private val orderRepository: OrderRepository) {

    operator fun invoke(): Flow<NetworkResponseState<List<Order>>>{
       return orderRepository.getOrder()
    }


}