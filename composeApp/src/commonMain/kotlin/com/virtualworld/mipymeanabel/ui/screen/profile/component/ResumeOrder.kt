package com.virtualworld.mipymeanabel.ui.screen.profile.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.virtualworld.mipymeanabel.data.dto.Order


@Composable
fun ResumeOrder(ordersState: List<Order>) {

    Column () {

        Text(ordersState.toString())



    }









}