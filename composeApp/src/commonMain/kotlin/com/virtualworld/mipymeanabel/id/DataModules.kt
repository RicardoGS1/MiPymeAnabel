package com.virtualworld.mipymeanabel.id

import com.virtualworld.mipymeanabel.data.repository.remote.FirebaseRepository
import com.virtualworld.mipymeanabel.data.repository.remote.FirebaseRepositoryImp
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import com.virtualworld.mipymeanabel.domain.GetProductSearchUseCase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModules = module {

    single<FirebaseDataSource> { FirebaseDataSourceImpl() }
    single<FirebaseRepository> { FirebaseRepositoryImp(get()) }

    singleOf (::GetProductSearchUseCase)
   // singleOf(::FirebaseRepositoryImp)
}