package com.virtualworld.mipymeanabel.data.source.local

import com.virtualworld.mipymeanabel.data.databese.TodoDao
import com.virtualworld.mipymeanabel.data.dto.ProductInfo
import kotlinx.coroutines.flow.Flow

class RoomDataSourceImpl(private val todoDao: TodoDao) : RoomDataSource {

    override fun getInfoProducts() : Flow<List<ProductInfo>> {
      return  todoDao.getAllProductInfoFlow()
    }

    override fun getInfoProductById(id :Long): Flow<ProductInfo?> {
        return todoDao.getInfoProductById(id)
    }

    override suspend fun updateInfoProduct(productInfo: ProductInfo){
        todoDao.insert(productInfo)
    }


}