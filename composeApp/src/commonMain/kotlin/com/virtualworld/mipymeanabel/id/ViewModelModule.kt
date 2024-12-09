package com.virtualworld.mipymeanabel.id

import com.virtualworld.mipymeanabel.ui.screen.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
}

