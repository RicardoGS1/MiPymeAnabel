package com.virtualworld.mipymeanabel.ui.screen.cart.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDatePicker(visible: Boolean, changerSelectDate: (Boolean) -> Unit) {

    val datePickerState = rememberDatePickerState()



    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { fullHeight -> -fullHeight },
        exit = slideOutVertically { fullHeight -> -fullHeight }
    ) {

        Box(Modifier.fillMaxSize().padding(horizontal = 8.dp).clickable { changerSelectDate(false) }) {
            Column {

                Text("Selccione la fecha de recojida del pedido")

                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.fillMaxWidth(),
                )

                Button(onClick = {}){
                    Text("Confirmar")
                }
            }

        }
    }


}