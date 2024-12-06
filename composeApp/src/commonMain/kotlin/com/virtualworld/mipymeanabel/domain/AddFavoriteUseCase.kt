package com.virtualworld.mipymeanabel.domain

import androidx.room.Dao
import com.virtualworld.mipymeanabel.data.source.local.AppDatabase
import com.virtualworld.mipymeanabel.data.source.local.AppDatabaseConstructor
import com.virtualworld.mipymeanabel.data.source.local.TodoDao
import com.virtualworld.mipymeanabel.data.source.local.TodoEntity


class AddFavoriteUseCase(private val db: TodoDao) {

 //   val db = getDatabaseBuilder(this).build

  //  private val todoDao: TodoDao()


    // private val factory: DatabaseFactory = DatabaseFactory


   // private var database: AppDatabase =  databaseFactory


    suspend fun aa(){

//        db.insert(
//
//            TodoEntity(
//                id = 1,
//                title = "",
//                content = "n"
//            )
//        )

      println(db.count())


    }

//    suspend operator fun invoke() {
//        dao.insert(TodoEntity(
//            id = 1,
//            title = "",
//            content = ""
//        ))
//    }



}