package com.virtualworld.mipymeanabel.id

import com.virtualworld.mipymeanabel.ui.screen.cart.CartViewModel
import com.virtualworld.mipymeanabel.ui.screen.detail.DetailViewModel
import com.virtualworld.mipymeanabel.ui.screen.home.HomeViewModel
import com.virtualworld.mipymeanabel.ui.screen.profile.ProfileViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module


val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
    viewModelOf(::DetailViewModel)
    viewModelOf(::CartViewModel)
    viewModelOf(::ProfileViewModel)
}

