package com.virtualworld.mipymeanabel.data.source.local

import com.virtualworld.mipymeanabel.data.databese.TodoDao
import com.virtualworld.mipymeanabel.data.dto.ProductInfo
import kotlinx.coroutines.flow.Flow

class RoomDataSource(private val todoDao: TodoDao) {

    fun getInfoProducts() : Flow<List<ProductInfo>> {

      return  todoDao.getAllProductInfoFlow()

    }

    fun getInfoProductById(id :Long): Flow<ProductInfo?> {

        return todoDao.getInfoProductById(id)

    }

    suspend fun updateFavorite(productInfo: ProductInfo){
        todoDao.insert(productInfo)
    }


}