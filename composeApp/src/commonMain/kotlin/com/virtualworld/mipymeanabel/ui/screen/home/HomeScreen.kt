package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
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
    val searchText by homeViewModel.searchText.collectAsState()

    val listState = rememberLazyGridState()

    val searchBarVisible by remember { derivedStateOf { (listState.firstVisibleItemScrollOffset < 2) } }

    val updateSearchText = { newSearchText: String -> homeViewModel.updateSearchText(newSearchText) }


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

    var selectedCategory by remember { mutableStateOf<String>("Todos") }
    val updateSelectedCategory = { category: String -> selectedCategory = category }


    Surface(modifier = Modifier.fillMaxSize()) {


        Column() {


            // Barra de búsqueda
            AnimatedVisibility(visible = (searchBarVisible)  ) {
                SearchBar(searchText,updateSearchText)
            }

            AnimatedVisibility(visible = (searchBarVisible) && searchText.isEmpty()) {
                ImagePagerView(products.map { it.image })
            }

            SelectCategory(categories, selectedCategory,updateSelectedCategory)


            GridProducts(listState, products)

        }
    }
}











