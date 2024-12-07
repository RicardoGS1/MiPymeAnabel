package com.virtualworld.mipymeanabel.data.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductInfo(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val favorite: Boolean,
    val cart: Boolean,
)
