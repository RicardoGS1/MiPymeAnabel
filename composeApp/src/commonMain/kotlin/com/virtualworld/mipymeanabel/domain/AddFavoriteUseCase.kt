package com.virtualworld.mipymeanabel.domain

import com.virtualworld.mipymeanabel.data.databese.TodoDao

class AddFavoriteUseCase(private val db: TodoDao) {


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


}