package com.virtualworld.mipymeanabel.id

import com.virtualworld.mipymeanabel.ui.screen.home.HomeViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module



val viewModelsModule = module {
    viewModelOf(::HomeViewModel)
}

fun  initKoin(configuration: KoinAppDeclaration? = null) {
    startKoin{
        configuration?.invoke(this)
        modules(viewModelsModule)
    }
}