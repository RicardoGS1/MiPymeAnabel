package com.virtualworld.mipymeanabel.ui.screen.cart.component


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrderDatePicker(
    visible: Boolean,
    changerSelectDate: (Boolean) -> Unit,
    changerDateDelivery: (Long) -> Unit,
    changerSendOrder: (Boolean) -> Unit
) {

    val datePickerState = rememberDatePickerState()

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black

    AnimatedVisibility(visible = visible,
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight }) {

        Box(Modifier.fillMaxSize().clickable { changerSelectDate(false) }) {

            Box(
                Modifier.fillMaxWidth()
                    .background(
                        Brush.verticalGradient(listOf(primaryColor, darkerPrimaryColor)),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    ).clickable(enabled = false) {}
                    .align(Alignment.BottomCenter)
            ) {

                Column(
                    Modifier.padding(horizontal = 16.dp).padding(top = 8.dp),
                    //verticalArrangement = Arrangement.SpaceAround
                ) {

                    Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {

                        IconButton(onClick = { changerSelectDate(false) }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Text(

                            "Fecha de despacho",
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                            // fontWeight = FontWeight.Bold
                        )


                    }

                    Text(
                        "Selccione una fecha para recojer su pedido",
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )

                    DatePicker(
                        state = datePickerState,
                        modifier = Modifier.padding(top = 16.dp)
                            .clip(shape = RoundedCornerShape(16.dp)),
                    )

                    Button(
                        onClick = {
                            if (datePickerState.selectedDateMillis != null) {
                                datePickerState.selectedDateMillis?.let { changerDateDelivery(it) }
                                changerSendOrder(true)
                                changerSelectDate(false)
                            } else {
                                scope.launch {
                                    snackBarHostState.showSnackbar(
                                        message = "Por favor, seleccione una fecha.",
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                        },
                        shape = RoundedCornerShape(32.dp),
                        modifier = Modifier.padding(bottom = 16.dp).padding(vertical = 24.dp)

                            .border(
                                width = 1.dp, color = Color.White, shape = RoundedCornerShape(32.dp)
                            ).height(38.dp).align(alignment = Alignment.CenterHorizontally),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent,
                            contentColor = Color.White
                        )
                    ) {
                        Text("Continuar >")
                    }

                }

                SnackbarHost(
                    hostState = snackBarHostState,
                    modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 20.dp)
                )

            }
        }
    }

}