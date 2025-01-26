package com.virtualworld.mipymeanabel.ui.screen.detailOrder

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import com.virtualworld.mipymeanabel.domain.models.OrderDetail
import com.virtualworld.mipymeanabel.ui.screen.common.model.ScreenStates
import com.virtualworld.mipymeanabel.ui.screen.utils.convertMillisToDate

@Composable
fun DetailOrderScreen(detailOrderViewModel: DetailOrderViewModel) {

    val orderState by detailOrderViewModel.orderState.collectAsStateWithLifecycle()


    when (orderState) {
        is ScreenStates.Error -> Text("Error")
        is ScreenStates.Loading -> Text("Loading")
        is ScreenStates.Success -> DetailOrder((orderState as ScreenStates.Success<OrderDetail>).result)

    }


}

@Composable
fun DetailOrder(order: OrderDetail) {

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(top = 40.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        item {
            OrderHeader(order)
        }
        items(order.listOrderProducts.size) { index ->
            ProductItem(order.listOrderProducts[index])
        }
    }

}

@Composable
fun OrderHeader(order: OrderDetail) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).padding(vertical = 4.dp),
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
                    .fillMaxWidth(), verticalArrangement = Arrangement.SpaceAround
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Numero de Orden:")
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
                    Text(text = "Estado:")
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
                    Text("Fecha de la Orden:")
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
                    Text("Fecha de Recogida:")
                    Text(convertMillisToDate(order.dateDelivery.toLong()))

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
                    Text("Importe Total:")
                    Text(order.importTotal)

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
                    Text("Unidades Totales:")
                    Text(order.unitTotal)

                }

            }


        }
    }

}

@Composable
fun ProductItem(product: OrderProducts) {

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp).padding(vertical = 4.dp),

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
                modifier = Modifier.width(120.dp).aspectRatio(2 / 2f).padding(4.dp).align(Alignment.CenterVertically)
                    .clip(MaterialTheme.shapes.small)
                    .clickable { })

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxSize().padding(8.dp)
            ) {


                Text(text = product.name, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                Text(text = "Price MN: ${product.priceMn}")
                Text(text = "Price USD: ${product.priceUsd}")
                Text(text = "Unidades: ${product.unit}")


            }
        }
    }
}


