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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SendOrder(
    visible: Boolean,
    changerSendOrder: (Boolean) -> Unit,
    totals: Map<String, Float>,
    onClickAddOrder: () -> Unit,
    dateDelivery: String,
    changerSelectDateVisible: (Boolean) -> Unit
) {

    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black

    AnimatedVisibility(visible = visible,
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight }) {

        Box(Modifier.fillMaxSize().clickable { changerSendOrder(false) }) {

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

                        IconButton(onClick = {
                            changerSendOrder(false)
                            changerSelectDateVisible(true)
                        }) {
                            Icon(
                                Icons.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Text(

                            "Finalizar Orden",
                            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                            fontSize = 24.sp,
                            color = Color.White,
                            textAlign = TextAlign.Center
                            // fontWeight = FontWeight.Bold
                        )


                    }

                    Text(
                        "Revise su pedido y a continucion envielo para completar la orden",
                        modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center
                    )


                    Text(
                        "Fecha de despacho: $dateDelivery",
                        fontSize = 18.sp,
                        color = Color.White,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(top = 16.dp).padding(bottom = 8.dp)
                            .fillMaxWidth()
                    )

                    Totals(totals)


                    Button(
                        onClick = {
                            onClickAddOrder()
                            changerSendOrder(false)
                        },
                        shape = RoundedCornerShape(32.dp),
                        modifier = Modifier.padding(bottom = 16.dp).padding(vertical = 24.dp)

                            .border(
                                width = 1.dp, color = Color.White, shape = RoundedCornerShape(32.dp)
                            ).height(38.dp).align(alignment = Alignment.CenterHorizontally),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent, // Fondo transparente
                            contentColor = Color.White // Color del texto (opcional)
                        )
                    )
                    {
                        Text("Enviar Orden")
                    }


                }

            }
        }

    }

}
