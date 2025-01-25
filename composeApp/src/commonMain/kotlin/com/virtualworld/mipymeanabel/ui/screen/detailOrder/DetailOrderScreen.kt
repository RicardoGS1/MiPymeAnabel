package com.virtualworld.mipymeanabel.ui.screen.detailOrder

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.ui.screen.common.model.ScreenStates

@Composable
fun DetailOrderScreen( detailOrderViewModel: DetailOrderViewModel ){

    val orderState by detailOrderViewModel.orderState.collectAsStateWithLifecycle()


    when(orderState){
        is ScreenStates.Error -> Text("Error")
        is ScreenStates.Loading -> Text("Loading")
        is ScreenStates.Success -> Text( (orderState as ScreenStates.Success<Order>).result.listOrderProducts.toString() )

    }


}