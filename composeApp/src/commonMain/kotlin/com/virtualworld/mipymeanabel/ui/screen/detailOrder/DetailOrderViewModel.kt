package com.virtualworld.mipymeanabel.ui.screen.detailOrder

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.domain.models.OrderDetail
import com.virtualworld.mipymeanabel.domain.useCase.AuthUseCase
import com.virtualworld.mipymeanabel.domain.useCase.DeletedOrderUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetOrderByIdUseCase
import com.virtualworld.mipymeanabel.ui.screen.common.model.ScreenStates
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailOrderViewModel(
    private val orderId: String,
    private val deletedOrderUseCase: DeletedOrderUseCase,
    private val getOrderByIdUseCase: GetOrderByIdUseCase,
) : ViewModel() {

    private val _orderState = MutableStateFlow<ScreenStates<OrderDetail>>(ScreenStates.Loading)
    val orderState: StateFlow<ScreenStates<OrderDetail>> get() = _orderState.asStateFlow()

    init {

        getOrderById()

    }

    private fun getOrderById() {


        viewModelScope.launch {

            getOrderByIdUseCase(orderId).collect { order ->

                when (order) {
                    is NetworkResponseState.Error -> {   }
                    is NetworkResponseState.Loading -> {  }
                    is NetworkResponseState.Success -> _orderState.update {

                        ScreenStates.Success(order.result)

                    }
                }


            }

        }

    }


}