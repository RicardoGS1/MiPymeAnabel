package com.virtualworld.mipymeanabel.data.databese

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.virtualworld.mipymeanabel.data.dto.ProductInfo
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {
    @Insert
    suspend fun insert(item: ProductInfo)

    @Query("SELECT count(*) FROM ProductInfo")
    suspend fun count(): Int

    @Query("SELECT * FROM ProductInfo")
    fun getAllAsFlow(): Flow<List<ProductInfo>>
}