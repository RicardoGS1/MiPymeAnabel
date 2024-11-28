package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.virtualworld.mipymeanabel.data.model.Product
import com.virtualworld.mipymeanabel.ui.screen.home.component.GridProducts
import com.virtualworld.mipymeanabel.ui.screen.home.component.ImagePagerView
import com.virtualworld.mipymeanabel.ui.screen.home.component.SearchBar
import com.virtualworld.mipymeanabel.ui.screen.home.component.SelectCategory
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel()) {

    val products by homeViewModel.productsState.collectAsState()

    val listState = rememberLazyGridState()

    val searchBarVisible by remember { derivedStateOf { (listState.firstVisibleItemScrollOffset < 2) } }

    var searchText by remember { mutableStateOf("") }

    val updateSearchText = { newSearchText: String -> searchText = newSearchText }

    val categories = listOf(
        "Todos",
        "Ropa",
        "Zapato",
        "Utiles del Hogar",
        "Electrodomestico",
        "Ferreteria",
        "Juguetes",
        "Embases",
        "Comida"
    )

    var selectedCategory by remember { mutableStateOf<String?>("Todos") }

    val pagerState = rememberPagerState(
        initialPage = 1,
        pageCount = { products.size }
    )



    Surface(modifier = Modifier.fillMaxSize()) {


        Column() {


            // Barra de búsqueda
            AnimatedVisibility(visible = (searchBarVisible)) {
                SearchBar(searchText,updateSearchText)
            }

            AnimatedVisibility(visible = (searchBarVisible)) {
                ImagePagerView(products.map { it.image }, pagerState)
            }

            SelectCategory(categories, selectedCategory)


            GridProducts(listState, products)

        }
    }

}











