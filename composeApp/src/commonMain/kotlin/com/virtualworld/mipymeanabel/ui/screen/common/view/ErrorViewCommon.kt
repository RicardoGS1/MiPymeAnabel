package com.virtualworld.mipymeanabel.ui.screen.common.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ErrorViewCommon(messenger: String, onRefresh: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize().height(180.dp).padding(horizontal = 8.dp, vertical = 4.dp),
        contentAlignment = Alignment.Center
    ) {

        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.Center) {
            Text(
                "Ocurrio un problema intentelo mas tarde ($messenger)",
                textAlign = TextAlign.Center
            )

            IconButton(onClick = {onRefresh()}, modifier = Modifier.align(Alignment.CenterHorizontally)){
                Icon(Icons.Filled.Refresh, "")
            }

        }
    }
}