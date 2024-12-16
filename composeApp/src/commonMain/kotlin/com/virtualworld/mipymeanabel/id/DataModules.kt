package com.virtualworld.mipymeanabel.id

import androidx.room.RoomDatabase
import com.virtualworld.mipymeanabel.data.repository.ProductRepository
import com.virtualworld.mipymeanabel.data.repository.ProductRepositoryImp
import com.virtualworld.mipymeanabel.data.databese.AppDatabase
import com.virtualworld.mipymeanabel.data.databese.TodoDao
import com.virtualworld.mipymeanabel.data.source.local.RoomDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import com.virtualworld.mipymeanabel.domain.AddCartUseCase
import com.virtualworld.mipymeanabel.domain.AddFavoriteUseCase
import com.virtualworld.mipymeanabel.domain.GetAllProductUseCase
import com.virtualworld.mipymeanabel.domain.GetProductByIdUseCase
import com.virtualworld.mipymeanabel.domain.GetProductCartUseCase
import dev.gitlive.firebase.Firebase
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

    single<FirebaseDataSource> { FirebaseDataSourceImpl(get()) }
    single<ProductRepository> { ProductRepositoryImp(get(),get()) }

    singleOf (::RoomDataSource)


    factoryOf (::GetAllProductUseCase)
    factoryOf(::AddFavoriteUseCase)
    factoryOf (::GetProductByIdUseCase)
    factoryOf (::AddCartUseCase)
    factoryOf(::GetProductCartUseCase)

}

