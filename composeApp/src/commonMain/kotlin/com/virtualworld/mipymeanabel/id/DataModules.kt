package com.virtualworld.mipymeanabel.id

import androidx.room.RoomDatabase
import com.virtualworld.mipymeanabel.data.databese.AppDatabase
import com.virtualworld.mipymeanabel.data.databese.TodoDao
import com.virtualworld.mipymeanabel.data.repository.AuthRepository
import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import com.virtualworld.mipymeanabel.data.repository.ProductRepositoryImp
import com.virtualworld.mipymeanabel.data.source.local.RoomDataSource
import com.virtualworld.mipymeanabel.data.source.local.RoomDataSourceImpl
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseAuthDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseAuthDataSourceImpl
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import com.virtualworld.mipymeanabel.domain.useCase.AddCartUseCase
import com.virtualworld.mipymeanabel.domain.useCase.AddFavoriteUseCase
import com.virtualworld.mipymeanabel.domain.useCase.AuthUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetAllProductUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetProductByIdUseCase
import com.virtualworld.mipymeanabel.domain.useCase.GetProductCartUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val dataModules = module {


    single<TodoDao> {
        val dbBuilder = get<RoomDatabase.Builder<AppDatabase>>()
        dbBuilder.build().getDao()
    }

    single { Firebase.firestore }
    single { Firebase.auth }

    //Interface
    single<FirebaseDataSource> { FirebaseDataSourceImpl(get()) }
    single<FirebaseAuthDataSource> { FirebaseAuthDataSourceImpl(get()) }
    single<ProductRepository> { ProductRepositoryImp(get(),get()) }
    single<RoomDataSource> { RoomDataSourceImpl(get()) }


    singleOf(::AuthRepository)
    singleOf( ::FirebaseAuthDataSourceImpl )


    factoryOf (::GetAllProductUseCase)
    factoryOf(::AddFavoriteUseCase)
    factoryOf (::GetProductByIdUseCase)
    factoryOf (::AddCartUseCase)
    factoryOf(::GetProductCartUseCase)
    factoryOf(::AuthUseCase)



}

