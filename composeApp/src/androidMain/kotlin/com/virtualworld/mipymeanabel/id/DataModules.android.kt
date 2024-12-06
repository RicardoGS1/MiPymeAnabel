package com.virtualworld.mipymeanabel.id

import com.virtualworld.mipymeanabel.data.source.local.getDatabaseBuilder
import org.koin.core.module.Module
import org.koin.dsl.module

actual val nativeModule = module  {

    single { getDatabaseBuilder(get()) }


}