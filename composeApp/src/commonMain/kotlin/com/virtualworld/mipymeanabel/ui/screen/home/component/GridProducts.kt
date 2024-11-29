package com.virtualworld.mipymeanabel.ui.screen.home.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.virtualworld.mipymeanabel.data.model.Product

@Composable
fun GridProducts(
    listState: LazyGridState,
    products: List<Product>
) {
    LazyVerticalGrid(
        state = listState,
        columns = GridCells.Adaptive(180.dp),
        horizontalArrangement = Arrangement.spacedBy(14.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp)

    ) {

        items(products, key = { it.idp }) {
            ProductItem(
                product = it,
                onProductClicked = {},
                onFavoriteClicked = {}
            )
        }

    }
}

@Composable
fun ProductItem(product: Product, onProductClicked: () -> Unit,onFavoriteClicked: () -> Unit) {

   val isFavorite by remember { mutableStateOf(false) }

    Box(){
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.onSecondary),
        elevation = CardDefaults.cardElevation(0.dp),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
        ),
    )
    {

        Column(modifier = Modifier.padding(4.dp)) {
            AsyncImage(
                model = product.image,
                contentDescription = product.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(2 / 2.5f)
                    .clip(MaterialTheme.shapes.small)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                        shape = MaterialTheme.shapes.small
                    )

            )
            Text(
                text = product.name,
                minLines = 2,
                maxLines = 2,
                style = MaterialTheme.typography.titleLarge,
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp).fillMaxWidth(),
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = product.priceUsd + " USD",
                maxLines = 1,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(horizontal = 4.dp).fillMaxWidth(),
                color = Color.Red.copy(alpha = 0.8f),
                fontWeight = FontWeight.Bold,
            )

        }


    }

        IconButton(
            onClick = onFavoriteClicked,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(0.dp) // Ajusta el padding según tus necesidades
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, // Cambia el icono según el estado de favorito
                contentDescription = "Agregar a favoritos",
                tint = Color.Red,// O el color que desees
                modifier = Modifier.size(32.dp)
            )
        }

    }

}
