package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.virtualworld.mipymeanabel.ui.screen.cart.component.LazyColumnCart
import com.virtualworld.mipymeanabel.ui.screen.cart.component.OrderDatePicker
import com.virtualworld.mipymeanabel.ui.screen.cart.component.ResumeCart
import com.virtualworld.mipymeanabel.ui.screen.cart.component.SendOrder
import kotlinx.coroutines.delay

@Composable
fun CartScreen(
    cartViewModel: CartViewModel,
    onProductClicked: (String) -> Unit,
    navProfiler: () -> Unit
) {

    val products by cartViewModel.productsState.collectAsStateWithLifecycle()

    val isAuthenticate by cartViewModel.isAuthenticate.collectAsStateWithLifecycle()

    val quantity by cartViewModel.quantity.collectAsStateWithLifecycle()
    val updateQuantity = { idp: Long, unit: Int -> cartViewModel.updateQuantity(idp, unit) }

    val totals by cartViewModel.totals.collectAsStateWithLifecycle()

    val selectDateVisible = remember { mutableStateOf(false) }
    val changerSelectDateVisible = { visible: Boolean -> selectDateVisible.value = visible }
    val dateDelivery by cartViewModel.dateDelivery.collectAsStateWithLifecycle("")
    val changerDateDelivery = { date: Long -> cartViewModel.changerDateDelivery(date) }

    val sendOrderVisible = remember { mutableStateOf(false) }
    val changerSendOrderVisible = { visible: Boolean -> sendOrderVisible.value = visible }

    val onClickAddOrder = { cartViewModel.onClickAddOrder() }

    var visible by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = Unit) {
        delay(200)
        visible = true
    }

    Box(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {

        LazyColumnCart(products, quantity, updateQuantity, onProductClicked)

        ResumeCart(visible, totals, selectDateVisible, Modifier.align(Alignment.BottomCenter),isAuthenticate,navProfiler)

        OrderDatePicker(
            selectDateVisible.value,
            changerSelectDateVisible,
            changerDateDelivery,
            changerSendOrderVisible
        )

        SendOrder(
            sendOrderVisible.value,
            changerSendOrderVisible,
            totals,
            onClickAddOrder,
            dateDelivery,
            changerSelectDateVisible
        )

    }
}







