package com.virtualworld.mipymeanabel.domain.useCase

import com.virtualworld.mipymeanabel.data.repository.ProductRepository

class AddFavoriteUseCase(private val repositoryImp: ProductRepository) {

    suspend fun addFavorite(id: Long){

        repositoryImp.changerFavorite(id)

    }


}