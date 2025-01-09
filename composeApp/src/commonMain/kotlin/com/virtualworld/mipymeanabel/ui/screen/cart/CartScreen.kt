package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import com.virtualworld.mipymeanabel.ui.screen.cart.component.OrderDatePicker
import kotlinx.coroutines.delay
import kotlin.random.Random

@Composable
fun CartScreen(cartViewModel: CartViewModel, onProductClicked: (String) -> Unit) {


    val quantity by cartViewModel.quantity.collectAsStateWithLifecycle()

    val totals by cartViewModel.totals.collectAsStateWithLifecycle()

    val products by cartViewModel.productsState.collectAsStateWithLifecycle()

    val dateDelivery by cartViewModel.dateDelivery.collectAsStateWithLifecycle("")
    val changerDateDelivery = { date: Long -> cartViewModel.changerDateDelivery(date) }

    val updateQuantity = { idp: Long, unit: Int -> cartViewModel.updateQuantity(idp, unit) }

    val selectDate = remember { mutableStateOf(false) }
    val changerSelectDate = { visible: Boolean -> selectDate.value = visible }

    val sendOrder = remember { mutableStateOf(false) }
    val changerSendOrder = { visible: Boolean -> sendOrder.value = visible }


    val onClickAddOrder = {  cartViewModel.onClickAddOrder() }

    val colorSurface = MaterialTheme.colorScheme.primary

    val scale = remember { mutableStateOf(0.1f) }
    val animatedScale by animateFloatAsState(
        targetValue = scale.value, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
        )
    )

    val offsetY = remember { mutableStateOf(0f) }
    val animatedOffsetY by animateFloatAsState(
        targetValue = offsetY.value, animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessLow
        )
    )

    LaunchedEffect(Unit) {
        scale.value = 0.1f // Escala inicial
        offsetY.value = 100f // Desplazamiento vertical inicial
        delay(200) // Retraso para que la animación sea visible
        scale.value = 1f // Escala final
        offsetY.value = 0f // Desplazamiento vertical final
    }


    Box(modifier = Modifier.fillMaxSize().padding(top = 40.dp)) {

        if (products.isNotEmpty()) {
            LazyColumn(Modifier.fillMaxSize()) {
                items(products, key = { it.idp }) { product ->
                    ItemProduct(product, updateQuantity, quantity, onProductClicked)
                }

                item {
                    Spacer(Modifier.height(180.dp))
                }
            }
        } else {
            Column(
                Modifier.align(Alignment.Center), horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = Icons.Filled.ShoppingCart,
                    contentDescription = "No hay productos en el carrito",
                    modifier = Modifier.size(90.dp),
                    tint = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f)

                )
                Text(text = "No hay productos en el carrito")
            }
        }


        Column(
            Modifier
                //.blur(radius = 1.dp)
                //.clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                .align(Alignment.BottomCenter),

            ) {

            val primaryColor = MaterialTheme.colorScheme.primary
            val darkerPrimaryColor = Color.Black


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

//                    TextButton(onClick = { selectDate.value = true }) {
//                        Text(
//                            text = if (dateDelivery.isEmpty()) "Agregar Fecha Recojida" else "Fecha Recojida: $dateDelivery",
//                            color = Color.White
//                        )
//                    }

                    Button(
                        onClick = {
                            // if (dateDelivery.isNotBlank()) cartViewModel.onClickAddOrder()
                            // else
                            selectDate.value = true
                        },
                        shape = RoundedCornerShape(32.dp),
                        modifier = Modifier.padding(bottom = 16.dp)

                            .border(
                                width = 1.dp, color = Color.White, shape = RoundedCornerShape(32.dp)
                            ).height(38.dp),

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent, // Fondo transparente
                            contentColor = Color.White // Color del texto (opcional)
                        )
                    ) {
                        Text("Planificar pedido ->")
                    }


                }
            }
        }


        OrderDatePicker(selectDate.value, changerSelectDate, changerDateDelivery, changerSendOrder)

        SendOrder(sendOrder.value, changerSendOrder , totals ,onClickAddOrder)


    }
}

@Composable
fun SendOrder(
    visible: Boolean,
    changerSendOrder: (Boolean) -> Unit,
    totals: Map<String, Float>,
    onClickAddOrder: () -> Unit
) {

    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black

    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight }
    ) {

        Box(Modifier.fillMaxSize().padding(top = 64.dp).background(
            Brush.verticalGradient(listOf(primaryColor, darkerPrimaryColor)),
            shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ).clickable { changerSendOrder(false) }) {


            Column(Modifier.fillMaxSize().padding(horizontal = 16.dp).padding(top = 60.dp)) {

                Text(
                    "2- Enviar pedido",
                    fontSize = 32.sp,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )





                Totals(totals)


                Button(
                    onClick = {
                        onClickAddOrder()
                        changerSendOrder(false) },
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
                    Text("Enviar Orden")
                }




            }

        }


    }

}


@Composable
fun Modifier.granulado(
    granularity: Int = 10000, color: Color = Color.Gray.copy(alpha = 1f)
): Modifier = remember(granularity, color) {
    drawBehind {
        val random = Random.Default
        for (i in 0 until granularity) {
            val x = random.nextFloat() * size.width
            val y = random.nextFloat() * size.height
            drawCircle(color, radius = 1f, center = Offset(x, y))
        }
    }
}

@Composable
private fun Totals(totals: Map<String, Float>) {


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


@Composable
private fun ItemProduct(
    product: ProductCart,
    updateQuantity: (Long, Int) -> Unit,
    quantity: Map<Long, Int>,
    onProductClicked: (String) -> Unit,

    ) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).padding(vertical = 4.dp)
            .height(120.dp),
        border = BorderStroke(color = MaterialTheme.colorScheme.primary.copy(0.2f), width = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.05f
            )
        )
    ) {
        Row() {
            AsyncImage(model = product.image,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight().aspectRatio(2 / 2f).padding(4.dp)
                    .clip(MaterialTheme.shapes.small)
                    .clickable { onProductClicked(product.idp.toString()) })

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {

                //Title
                Text(
                    text = product.name, style = MaterialTheme.typography.titleLarge.copy(
                        fontWeight = FontWeight.Bold
                    )
                )

                //Quantity
                Quantity(updateQuantity, quantity, product)


            }

        }


    }
}


@Composable
private fun Quantity(
    updateQuantity: (Long, Int) -> Unit, quantity: Map<Long, Int>, product: ProductCart
) {

    Row(
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Button(
            onClick = { updateQuantity(product.idp, -1) },
            modifier = Modifier.border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)
            ).size(42.dp),
            shape = RectangleShape,
            contentPadding = PaddingValues(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),


            ) {
            Text(
                text = "-",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 2
            )
        }

        Text(
            text = quantity[product.idp].toString(),
            style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.height(42.dp).wrapContentWidth().widthIn(min = 42.dp)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary).padding(4.dp)
        )

        Button(
            onClick = { updateQuantity(product.idp, 1) },
            modifier = Modifier.border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(topEnd = 12.dp, bottomEnd = 12.dp)
            ).size(42.dp),
            shape = RectangleShape,
            contentPadding = PaddingValues(2.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent
            ),


            ) {
            Text(
                text = "+",
                fontSize = MaterialTheme.typography.headlineMedium.fontSize,
                color = MaterialTheme.colorScheme.onSurface
            )
        }


        //Price
        Text(
            text = product.priceUSD.toString() + " USD",
            modifier = Modifier.fillMaxWidth().align(alignment = Alignment.CenterVertically),
            textAlign = TextAlign.End,
            style = MaterialTheme.typography.titleLarge.copy(

            ),
            color = Color.Red.copy(alpha = 0.8f),
        )


    }
}