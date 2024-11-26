package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.virtualworld.mipymeanabel.data.model.Product
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import kotlin.text.toFloat

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {

    val products by homeViewModel.productsState.collectAsState()


    Surface(modifier = Modifier.fillMaxSize()) {

        val listState = rememberLazyGridState()


        val searchBarVisible =
            remember { derivedStateOf { (listState.firstVisibleItemScrollOffset < 2) } }



        Column() {

            // Barra de búsqueda
            AnimatedVisibility(visible = (searchBarVisible.value)) {
                SearchBar()
            }


            // Contenido desplazable
            LazyVerticalGrid(
                state = listState,
                columns = GridCells.Adaptive(120.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .padding(horizontal = 8.dp)

            ) {

                items(products, key = { it.idp }) {
                    ProductItem(
                        product = it,
                        onProductClicked = {},
                    )
                }

            }

        }
    }

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf("") }



    Row(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp).padding(bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(
            value = searchText,
            onValueChange = { searchText = it },
            label = { Text("Buscar") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Buscar..."
                )
            },
            modifier = Modifier
                .weight(1f)
                .padding(end = 16.dp),
            shape = RoundedCornerShape(16.dp),
        )

        IconButton(onClick = { /* Acción al hacer clic en el botón */ }) {
            Icon(
                imageVector = Icons.Filled.Menu, // Icono del botón
                contentDescription = "Filtrar"
            )
        }


    }
}

@Composable
fun ProductItem(product: Product, onProductClicked: () -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        border = BorderStroke(
            width = 2.dp,
            color = MaterialTheme.colorScheme.primary.copy(alpha = 0.15f)
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
                        width = 2.dp,
                        color = MaterialTheme.colorScheme.primary.copy(alpha = 0.2f),
                        shape = MaterialTheme.shapes.small
                    )

            )
            Text(
                text = product.name,
                minLines = 2,
                maxLines = 2,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 16.sp,
                modifier = Modifier.padding(4.dp).fillMaxWidth()
            )
            Text(
                text = product.priceUsd + " USD",
                maxLines = 1,
                textAlign = TextAlign.End,
                modifier = Modifier.padding(horizontal = 8.dp).fillMaxWidth()
            )

        }
    }

}





