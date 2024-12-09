package com.virtualworld.mipymeanabel.ui.screen.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.virtualworld.mipymeanabel.ui.screen.home.component.GridProducts
import com.virtualworld.mipymeanabel.ui.screen.home.component.ImagePagerView
import com.virtualworld.mipymeanabel.ui.screen.home.component.SearchBar
import com.virtualworld.mipymeanabel.ui.screen.home.component.SelectCategory
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(homeViewModel: HomeViewModel = koinViewModel(),  onProductClicked: (String) -> Unit) {

    val products by homeViewModel.productsState.collectAsState()
    val categories by homeViewModel.categoryState.collectAsState()
    val selectedCategory by homeViewModel.selectedCategoryState.collectAsState()
    val searchText by homeViewModel.searchText.collectAsState()

    val listState = rememberLazyGridState()

    val searchBarVisible by remember { derivedStateOf { (listState.firstVisibleItemScrollOffset < 2) } }

    val updateSearchText = { newSearchText: String -> homeViewModel.updateSearchText(newSearchText) }

    val updateSelectedCategory = { category: String -> homeViewModel.updateSelectedCategory(category) }

    val onClickFavorite = { id:String -> homeViewModel.onClickFavorite(id) }



    Surface(modifier = Modifier.fillMaxSize()) {

        Column() {

            AnimatedVisibility(visible = (searchBarVisible)  ) {
                SearchBar(searchText,updateSearchText)
            }

            AnimatedVisibility(visible = (searchBarVisible) && searchText.isEmpty()) {
                ImagePagerView(products.map { it.image })
            }

            SelectCategory(categories, selectedCategory,updateSelectedCategory)


            GridProducts(listState, products, onClickFavorite,onProductClicked)

        }
    }
}



