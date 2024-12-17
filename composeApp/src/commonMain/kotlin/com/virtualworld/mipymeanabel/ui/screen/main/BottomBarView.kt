package com.virtualworld.mipymeanabel.ui.screen.main

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.virtualworld.mipymeanabel.navegation.navigateToBottomNavDestination

@Composable
fun BottomBarView(navHostController: NavHostController) {


    val items = BottomNavItem.getDestinations()
    val selectedItemIndex = remember { mutableStateOf(0) } // State to track selected item

    val currentBackStackEntry by navHostController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    LaunchedEffect(key1 = currentRoute) {
        selectedItemIndex.value = items.indexOfFirst { it.route == currentRoute }
    }



    NavigationBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface) // Color de fondo
            .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)) // Esquinas redondeadas
            .shadow(elevation = 4.dp),
    ) {

        items.forEachIndexed { index, item ->

            val iconColor by animateColorAsState(
                targetValue = if (selectedItemIndex.value == index) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
                }
            )

            NavigationBarItem(


                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title,
                        tint = iconColor
                    )
                },

                label = {
                    Text(
                        text = item.title,
                        style = if (selectedItemIndex.value == index) {
                            MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                        } else {
                            MaterialTheme.typography.bodyMedium
                        }
                    )
                },
                selected = selectedItemIndex.value == index,
                onClick = {
                    selectedItemIndex.value = index
                    navHostController.navigateToBottomNavDestination(item)
                   // navHostController.navigate(item.route)
                },
                alwaysShowLabel = false // To hide labels when not selected
            )
        }
    }
}

