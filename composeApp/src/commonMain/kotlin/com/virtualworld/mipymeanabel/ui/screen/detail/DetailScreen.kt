package com.virtualworld.mipymeanabel.ui.screen.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.virtualworld.mipymeanabel.data.dto.ProductAll

@Composable
fun DetailScreen( detailViewModel : DetailViewModel ){

    detailViewModel.a()

    Text(text = "dettalle")



}