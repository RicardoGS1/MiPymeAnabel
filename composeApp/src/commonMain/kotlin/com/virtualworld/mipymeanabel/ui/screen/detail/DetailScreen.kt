package com.virtualworld.mipymeanabel.ui.screen.detail

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun DetailScreen( detailViewModel : DetailViewModel ){

    val productState by detailViewModel.productState.collectAsState()
Box(){
    Column(modifier = Modifier
        .fillMaxSize()) {
        Box() {
            AsyncImage(
                model = productState.image,
                contentDescription = productState.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth().aspectRatio(2.5f / 2f)
                    .clip(RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp))


            )
            IconButton(
                onClick = { },//onFavoriteClicked(product.idp) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(0.dp) // Ajusta el padding según tus necesidades
            ) {
                Icon(
                    imageVector = if (productState.favorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder, // Cambia el icono según el estado de favorito
                    contentDescription = "Agregar a favoritos",
                    tint = Color.Red,// O el color que desees
                    modifier = Modifier.size(32.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(Modifier.padding(16.dp)) {
            Text(
                text = productState.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = productState.detail)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Price: $${productState.priceMn} MN", style  = MaterialTheme.typography.titleMedium, )
            Text(text = "Price: $${productState.priceUsd} USD", style  = MaterialTheme.typography.titleMedium)

        }

    }

    Button(
        onClick = { /* Agregar producto al carrito */ },
        shape =  RoundedCornerShape(8.dp) ,
        modifier = Modifier
            .align(Alignment.BottomCenter)
            .padding(16.dp).fillMaxWidth()

    ) {
        Text("Add to Cart")
    }
}
}


