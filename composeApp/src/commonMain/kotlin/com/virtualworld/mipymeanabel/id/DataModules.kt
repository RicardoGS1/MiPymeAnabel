package com.virtualworld.mipymeanabel.id

import androidx.room.RoomDatabase
import com.virtualworld.mipymeanabel.data.repository.remote.FirebaseRepository
import com.virtualworld.mipymeanabel.data.repository.remote.FirebaseRepositoryImp
import com.virtualworld.mipymeanabel.data.source.local.AppDatabase
import com.virtualworld.mipymeanabel.data.databese.TodoDao
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSourceImpl
import com.virtualworld.mipymeanabel.domain.AddFavoriteUseCase
import com.virtualworld.mipymeanabel.domain.GetAllProductUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module


val dataModules = module {


    single<TodoDao> {
        val dbBuilder = get<RoomDatabase.Builder<AppDatabase>>()
        dbBuilder.build().getDao()
    }

    single { Firebase.firestore }

    single<FirebaseDataSource> { FirebaseDataSourceImpl(get()) }
    single<FirebaseRepository> { FirebaseRepositoryImp(get()) }


    factoryOf (::GetAllProductUseCase)
    factoryOf(::AddFavoriteUseCase)


}

