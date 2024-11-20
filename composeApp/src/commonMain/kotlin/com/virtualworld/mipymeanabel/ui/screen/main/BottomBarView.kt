package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController

@Composable
fun BottomBarView(navHostController: NavHostController) {


    val items = BottomNavItem.getDestinations()
    val selectedItemIndex = remember { mutableStateOf(0) } // State to track selected item

    NavigationBar() {

        items.forEachIndexed { index, item ->

            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title) },
                selected = selectedItemIndex.value == index,
                onClick = { selectedItemIndex.value = index
                    navHostController.navigate(item.route)},
                alwaysShowLabel = false // To hide labels when not selected
            )
        }
    }
}

