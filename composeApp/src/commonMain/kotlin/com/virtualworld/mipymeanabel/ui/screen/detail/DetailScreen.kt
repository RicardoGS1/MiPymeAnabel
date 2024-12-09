package com.virtualworld.mipymeanabel.ui.screen.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun DetailScreen( detailViewModel : DetailViewModel ){

    val productState by detailViewModel.productState.collectAsState()

    Column(modifier = Modifier
        .fillMaxSize()) {
        AsyncImage(
            model = productState.image,
            contentDescription = productState.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().aspectRatio(2.5f / 2f)
                .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))


        )
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
            Text(text = "Price: $${productState.priceUsd}")

        }

    }
}


