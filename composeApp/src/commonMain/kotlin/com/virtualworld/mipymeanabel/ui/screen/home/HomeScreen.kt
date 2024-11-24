package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen( homeViewModel: HomeViewModel = koinViewModel()){

    val products by  homeViewModel.productsState.collectAsState()

    Text(text = products.toString())



//        val listState = rememberLazyGridState()
//        LazyVerticalGrid(
//            state = listState,
//            columns = GridCells.Fixed(2),
//            modifier = Modifier.fillMaxSize(),
//        ) {
//            items(products.size, key = { products[it].id }) {
//                ProductItem(
//                    product = products[it],
//                    onProductClicked = {},
//                )
//            }
//        }



    }

//
//@Composable
//fun ProductItem(
//    product: Products,
//    onProductClicked: () -> Unit,
//) {
//    Card(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(16.dp).clickable {  },
//        elevation = CardDefaults.cardElevation(5.dp),
//        shape = RoundedCornerShape(5.dp),
//    ) {
//        Column(
//            modifier = Modifier.clickable {  }
//                .fillMaxWidth()
//                .padding(8.dp),
//        ) {
//            AsyncImage(
//                model = product.imageUrl,
//                contentDescription = "stringResource(id = R.string.product_image_content),",
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .height(200.dp),
//                contentScale = ContentScale.Crop,
//            )
//
//            Text(
//                text = product.title,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 1,
//                style = TextStyle(
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 16.sp,
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//            )
//
//            Text(
//                text = product.description,
//                overflow = TextOverflow.Ellipsis,
//                maxLines = 2,
//                style = TextStyle(fontSize = 14.sp),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//            )
//
//
//            Text(
//                text = "${product.price} MN",
//                style = TextStyle(
//                    fontWeight = FontWeight.Bold,
//                    fontSize = 14.sp,
//                ),
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 8.dp),
//            )
//        }
//    }
//}




