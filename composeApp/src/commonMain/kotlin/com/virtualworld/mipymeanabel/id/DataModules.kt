package com.virtualworld.mipymeanabel.id

import com.virtualworld.mipymeanabel.data.repository.remote.FirebaseRepository
import com.virtualworld.mipymeanabel.data.repository.remote.FirebaseRepositoryImp
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import com.virtualworld.mipymeanabel.domain.GetAllProductUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val dataModules = module {

    single { Firebase.firestore }

    single<FirebaseDataSource> { FirebaseDataSourceImpl(get()) }
    single<FirebaseRepository> { FirebaseRepositoryImp(get()) }


    singleOf (::GetAllProductUseCase)
   // singleOf(::FirebaseRepositoryImp)
}