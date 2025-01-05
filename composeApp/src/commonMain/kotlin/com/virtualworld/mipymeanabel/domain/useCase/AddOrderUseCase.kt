package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.repository.OrderRepository

class AddOrderUseCase (private val orderRepository: OrderRepository) {

    suspend operator fun invoke(order: Order) {
        orderRepository.addOrder(order)
    }

}