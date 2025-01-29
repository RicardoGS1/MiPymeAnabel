package com.virtualworld.mipymeanabel.ui.screen.profile.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.ui.screen.utils.convertMillisToDate
import mipymeanabel.composeapp.generated.resources.Res
import mipymeanabel.composeapp.generated.resources.YoungSerif_Regular
import org.jetbrains.compose.resources.Font


@Composable
fun ResumeOrder(mail: String, ordersState: List<Order>, signOut: () -> Unit) {

    val visible = remember { mutableStateOf(false) }

    val primaryColor = MaterialTheme.colorScheme.surface
    val darkerPrimaryColor = MaterialTheme.colorScheme.primary


    LaunchedEffect(Unit) {
        visible.value = true
    }



    Box() {

        Box(Modifier.fillMaxWidth().height(40.dp).background(primaryColor))



        Box(Modifier.fillMaxSize().padding(top = 40.dp)) {

            LazyColumn() {

                item {


                    Box(
                        Modifier.fillMaxWidth()
                            .background(
                                Brush.verticalGradient(
                                    listOf(
                                        primaryColor,
                                        darkerPrimaryColor
                                    )
                                ),
                                shape = RoundedCornerShape(
                                    bottomStart = 0.dp,
                                    bottomEnd = 60.dp
                                )
                            ).clickable(enabled = false) {}
                            .align(Alignment.TopCenter)
                    ) {

                        Column(
                            Modifier.padding(horizontal = 16.dp).padding(bottom = 72.dp),
                            //verticalArrangement = Arrangement.SpaceAround
                        ) {
                            Text(
                                text = "Sesión",
                                style = MaterialTheme.typography.titleLarge,
                                //fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary,
                                fontSize = 32.sp,
                                fontFamily = FontFamily(Font(Res.font.YoungSerif_Regular)),
                                modifier = Modifier.padding(vertical = 8.dp)
                            )

                            Text("Usuario: $mail", style = MaterialTheme.typography.titleLarge)
                            Text("Correo: $mail", style = MaterialTheme.typography.titleMedium)
                            Text("Numero: 0000000", style = MaterialTheme.typography.titleMedium)


                        }

                        TextButton(
                            onClick = { signOut() },
                            Modifier.align(Alignment.BottomEnd).padding(end = 30.dp, bottom = 20.dp)
                        ) {
                            Text("Cerrar Sesion", color = MaterialTheme.colorScheme.onPrimary)
                        }


                    }


                }

                item {

                    Text(
                        text = "Ordenes",
                        style = MaterialTheme.typography.titleLarge,
                        //fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 32.sp,
                        fontFamily = FontFamily(Font(Res.font.YoungSerif_Regular)),
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )

                }

                items(ordersState) { order ->


                    ItemOrder(order)


                }

                item {
                    Spacer(Modifier.height(100.dp))
                }

            }


        }
    }


}

@Composable
fun ItemOrder(order: Order) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(vertical = 4.dp)
            .height(120.dp),
        border = BorderStroke(color = MaterialTheme.colorScheme.primary.copy(0.2f), width = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.05f
            )
        )
    ) {

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

            Column(
                Modifier.fillMaxHeight().padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth(0.8f), verticalArrangement = Arrangement.SpaceAround
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Numero de Orden:     ")
                    Text(text = order.number, fontWeight = FontWeight.Bold)

                }
                HorizontalDivider(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    thickness = 1.dp,
                    color = Color.LightGray
                )
                //State
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Estado:     ")
                    Text(text = order.state, fontWeight = FontWeight.Bold)

                }
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Fecha de la Orden:     ")
                    Text(convertMillisToDate(order.dateOrder.toLong()))

                }
                HorizontalDivider(
                    color = Color.LightGray,
                    thickness = 1.dp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text("Fecha de Recogida:     ")
                    Text(convertMillisToDate(order.dateDelivery.toLong()))

                }

            }



            Box(
                Modifier.fillMaxSize().background(MaterialTheme.colorScheme.primary).clickable { },
                contentAlignment = Alignment.Center,
            ) {


                Text("Mas", color = MaterialTheme.colorScheme.onPrimary)


            }

        }
    }


}


