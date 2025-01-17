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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
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
import com.virtualworld.mipymeanabel.ui.screen.common.view.AlertDialogCommon
import com.virtualworld.mipymeanabel.ui.screen.common.model.DataTotals

@Composable
fun ResumeCart(
    visible: Boolean,
    totals: DataTotals,
    selectDateVisible: MutableState<Boolean>,
    modifier: Modifier,
    isAuthenticate: Boolean,
    navProfiler: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }
    val changerShowDialog = { visible: Boolean -> showDialog = visible }


    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black


    if (totals.totalUSD > 0.0 || totals.totalMN > 0.0) {
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
                            Text("Planificar Orden >")
                        }


                    }
                }
            }
            AlertDialogCommon(showDialog, navProfiler, changerShowDialog)
        }
    }
}


@Composable
fun Totals(totals: DataTotals) {

    Column(Modifier.padding(horizontal = 12.dp)) {

        TotalRow(label = "Total en USD", total = totals.totalUSD.toString() + " USD")

        TotalRow(label = "Total en MN", total = totals.totalMN.toString() + " MN")

        TotalRow(label = "Unidades", total = totals.units.toString())

    }
}

@Composable
fun TotalRow(label: String, total: String) {

    val fontIndices = MaterialTheme.typography.titleMedium.copy(color = Color.White)
    val fontValues = MaterialTheme.typography.titleLarge.copy(color = Color.White)

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = fontIndices)
        Text(text = total, style = fontValues)
    }
}



