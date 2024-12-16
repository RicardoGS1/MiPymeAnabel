package com.virtualworld.mipymeanabel.ui.screen.cart

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage

@Composable
fun CartScreen(cartViewModel: CartViewModel) {

    var quantity by remember { mutableStateOf(1) }// temporal

    val products by cartViewModel.productsState.collectAsStateWithLifecycle()

    LazyColumn(modifier = Modifier.fillMaxSize()) {

        items(products, key = { it.idp }) { product ->

            Card(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).height(60.dp),
                border = BorderStroke(color = MaterialTheme.colorScheme.primary, width = 1.dp),
            ) {
                Row() {
                    AsyncImage(
                        model = product.image,
                        contentDescription = product.name,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxHeight().aspectRatio(2 / 2f)
                            .clip(MaterialTheme.shapes.small)

                    )

                    Column() {

                        Text(text = product.name, style = MaterialTheme.typography.titleLarge)
                        Row(
                            // ...
                        ) {
                            Button(onClick = { quantity-- }) {
                                Text("-")
                            }

                            Text(
                                text = quantity.toString(),
                                style = MaterialTheme.typography.headlineMedium
                            )

                            Button(onClick = { quantity++ }) {
                                Text("+")
                            }
                        }


                    }

                }


            }

        }


    }


}