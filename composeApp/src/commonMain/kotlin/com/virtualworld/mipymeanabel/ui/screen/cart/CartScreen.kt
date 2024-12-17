package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.virtualworld.mipymeanabel.domain.models.ProductCart

@Composable
fun CartScreen(cartViewModel: CartViewModel) {

    val quantity by cartViewModel.quantity.collectAsStateWithLifecycle()

    val totals by cartViewModel.totals.collectAsStateWithLifecycle()

    val products by cartViewModel.productsState.collectAsStateWithLifecycle()

    val updateQuantity = { idp: Long, unit: Int -> cartViewModel.updateQuantity(idp, unit) }

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {

            items(products, key = { it.idp }) { product ->
                ItemProduct(product, updateQuantity, quantity)
            }

            item {
                Totals(totals)
            }
        }

        Button(
            onClick = { cartViewModel.onClickAddOrder() },
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp).fillMaxWidth()

        ) {

            Text("Realizar pedido")
        }
    }
}

@Composable
private fun Totals(totals: Map<String, Float>) {


    val fontIndices = MaterialTheme.typography.titleLarge.copy()

    Column(modifier = Modifier.padding(18.dp)) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total en USD",
                style = fontIndices
            )
            Text(
                text = totals.get("totalUSD").toString() + "USD",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total en MN",
                style = fontIndices
            )
            Text(
                text = totals.get("totalMN").toString() + "MN",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Unidades",
                style = fontIndices
            )
            Text(
                text = totals.get("units")?.toInt().toString(),
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
                color = MaterialTheme.colorScheme.primary
            )
        }

    }
}


@Composable
private fun ItemProduct(
    product: ProductCart,
    updateQuantity: (Long, Int) -> Unit,
    quantity: Map<Long, Int>
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            .padding(vertical = 4.dp).height(120.dp),
        border = BorderStroke(color = MaterialTheme.colorScheme.primary.copy(0.6f), width = 1.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary.copy(
                alpha = 0.1f
            )
        )
    ) {
        Row() {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxHeight().aspectRatio(2 / 2f)
                    .padding(4.dp).clip(MaterialTheme.shapes.small)
            )

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
    updateQuantity: (Long, Int) -> Unit,
    quantity: Map<Long, Int>,
    product: ProductCart
) {

    Row(
        modifier = Modifier.padding(bottom = 4.dp)
    ) {
        Button(
            onClick = { updateQuantity(product.idp, -1) },
            modifier = Modifier.border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
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
            modifier = Modifier.height(42.dp).wrapContentWidth()
                .widthIn(min = 42.dp)
                .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                .padding(4.dp)
        )

        Button(
            onClick = { updateQuantity(product.idp, 1) },
            modifier = Modifier.border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.primary
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