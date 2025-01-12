package com.virtualworld.mipymeanabel.ui.screen.cart.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ResumeCart(
    visible: Boolean,
    totals: Map<String, Float>,
    selectDateVisible: MutableState<Boolean>,
    modifier: Modifier,
    isAuthenticate: Boolean,
    navProfiler: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }


    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black


    if (totals["totalUSD"]?.let { it > 0.0 } == true || totals["totalMN"]?.let { it > 0.0 } == true) {
        Box(modifier = modifier) {

            AnimatedVisibility(visible = visible,
                enter = slideInVertically { fullHeight -> fullHeight },
                exit = slideOutVertically { fullHeight -> fullHeight }) {

                Box(
                    Modifier.background(
                        Brush.verticalGradient(listOf(primaryColor, darkerPrimaryColor)),
                        shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
                    )
                ) {


                    Column(
                        Modifier.fillMaxWidth().padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {


                        Totals(totals)


                        Button(
                            onClick = {
                                if (isAuthenticate) {
                                    selectDateVisible.value = true
                                } else {

                                    showDialog = true
                                }
                            },
                            shape = RoundedCornerShape(32.dp),
                            modifier = Modifier.padding(bottom = 16.dp)

                                .border(
                                    width = 1.dp,
                                    color = Color.White,
                                    shape = RoundedCornerShape(32.dp)
                                ).height(38.dp),

                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color.Transparent, contentColor = Color.White //
                            ),

                            ) {
                            Text("Planificar pedido >")
                        }


                    }
                }
            }
            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { showDialog = false },
                    title = { Text("Registro necesario") },
                    text = { Text("Para continuar, debes registrarte o iniciar sesión.") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                showDialog = false
                                navProfiler()
                            }
                        ) {
                            Text("Registrarse")
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = { showDialog = false }
                        ) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun Totals(totals: Map<String, Float>) {


    val fontIndices = MaterialTheme.typography.titleMedium.copy(color = Color.White)
    val fontValues = MaterialTheme.typography.titleLarge.copy(color = Color.White)

    Column(Modifier.padding(horizontal = 12.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total en USD", style = fontIndices
            )
            Text(
                text = totals.get("totalUSD").toString() + " USD",
                style = fontValues,
                //color = MaterialTheme.colorScheme.primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total en MN", style = fontIndices
            )
            Text(
                text = totals.get("totalMN").toString() + " MN",
                style = fontValues,
                // color = MaterialTheme.colorScheme.primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Unidades", style = fontIndices
            )
            Text(
                text = totals.get("units")?.toInt().toString(),
                style = fontValues,
                //color = MaterialTheme.colorScheme.primary
            )
        }

        Spacer(Modifier.height(8.dp))

    }
}



