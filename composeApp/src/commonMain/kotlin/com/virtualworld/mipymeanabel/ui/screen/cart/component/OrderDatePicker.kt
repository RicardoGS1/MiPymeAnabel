package com.virtualworld.mipymeanabel.ui.screen.cart.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDatePicker(
    visible: Boolean,
    changerSelectDate: (Boolean) -> Unit,
    changerDateDelivery: (Long) -> Unit,
    changerSendOrder: (Boolean) -> Unit
) {

    val datePickerState = rememberDatePickerState()

    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight }
    ) {

        Box(Modifier.fillMaxSize().padding(top=64.dp).background(
            Brush.verticalGradient(listOf(primaryColor, darkerPrimaryColor)),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ).clickable { changerSelectDate(false) }) {

            Column(Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(top = 60.dp)) {

                Text(
                    "1- Selccione una fecha para",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    "recojer su pedido",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )

                DatePicker(
                    state = datePickerState,
                    modifier = Modifier.fillMaxWidth().padding(vertical = 24.dp)
                        .clip(shape = RoundedCornerShape(16.dp)),
                )

                Button(
                    onClick = {
                        datePickerState.selectedDateMillis?.let { changerDateDelivery (it) }
                        changerSendOrder(true)
                        changerSelectDate(false)},
                    shape = RoundedCornerShape(32.dp),
                    modifier = Modifier
                        .padding(bottom = 16.dp)

                        .border(
                            width = 1.dp,
                            color = Color.White,
                            shape = RoundedCornerShape(32.dp)
                        ).height(38.dp).align(alignment = Alignment.CenterHorizontally),

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Transparent, // Fondo transparente
                        contentColor = Color.White // Color del texto (opcional)
                    )
                )
                {
                    Text("Continuar ->")
                }
            }

        }
    }


}