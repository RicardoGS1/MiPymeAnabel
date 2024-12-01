package com.virtualworld.mipymeanabel.ui.screen.home.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun SelectCategory(
    categories: List<String>,
    selectedCategory: String?,
    updateSelectedCategory: (String) -> Unit
) {
    var selectedCategory1 = selectedCategory
    LazyRow(modifier = Modifier.padding(4.dp)) {
        categories.forEach {
            item {

                val buttonColor by animateColorAsState(
                    targetValue = if (selectedCategory1 == it) MaterialTheme.colorScheme.primary.copy(alpha = 0.9f) else MaterialTheme.colorScheme.secondary.copy(
                        alpha = 0.5f
                    ),
                    animationSpec = tween(durationMillis = 500)
                )

                Button(
                    onClick = { updateSelectedCategory(it) },
                    modifier = Modifier.padding(horizontal = 8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColor // Cambia el color del botón seleccionado
                    )
                ) {
                    Text(it, color = Color.White)
                }
            }
        }
    }
}
