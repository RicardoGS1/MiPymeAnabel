package com.virtualworld.mipymeanabel.ui.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RadialGradientShader
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.virtualworld.mipymeanabel.ui.screen.common.model.ScreenStates

@Composable
fun ImagePagerView(images: List<String>) {


    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { images.size }
    )


    Box(contentAlignment = Alignment.TopCenter) {

        HorizontalPager(
            state = pagerState,
            modifier = Modifier
                .fillMaxWidth().height(180.dp).padding(horizontal = 8.dp, vertical = 4.dp)
                .clip(shape = MaterialTheme.shapes.medium)

        ) { page ->
            AsyncImage(
                model = images[page],
                contentDescription = "Imagen ${page + 1}",
                modifier = Modifier.fillMaxWidth(),
                contentScale = ContentScale.FillWidth
            )

        }

    }
}
