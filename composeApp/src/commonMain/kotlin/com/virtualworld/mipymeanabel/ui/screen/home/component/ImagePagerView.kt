package com.virtualworld.mipymeanabel.ui.screen.home.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun ImagePagerView(images: List<String>) {

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { images.size }
    )



    Column {

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