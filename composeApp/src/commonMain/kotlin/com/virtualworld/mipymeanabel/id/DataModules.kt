package com.virtualworld.mipymeanabel.id

import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val dataModules = module {
    factoryOf (::FirebaseDataSourceImpl )
}