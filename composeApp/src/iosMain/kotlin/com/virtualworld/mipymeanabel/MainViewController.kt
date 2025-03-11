package com.virtualworld.mipymeanabel

import androidx.compose.ui.window.ComposeUIViewController
import com.virtualworld.mipymeanabel.id.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {

    val screen = null //agregar al configurar notifications in ios

    App(screen)
}