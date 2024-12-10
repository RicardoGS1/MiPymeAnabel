package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.data.dto.ProductInfo
import com.virtualworld.mipymeanabel.data.mapper.toProductAll
import com.virtualworld.mipymeanabel.data.source.local.RoomDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class ProductRepositoryImp(
    private val firebaseDataSource: FirebaseDataSource,
    private val roomDataSource: RoomDataSource
) : ProductRepository {

    private var favoriteMap: Map<Long, Boolean> = emptyMap()
    private var cartMap: Map<Long, Boolean> = emptyMap()


    override suspend fun changerCart(id: Long) {
        TODO("Not yet implemented")
    }

    override suspend fun changerFavorite(id: Long) {

        roomDataSource.updateFavorite(
            ProductInfo(
                id = id,
                favorite = !(favoriteMap[id] ?: false),
                cart = cartMap[id] ?: false
            )
        )
    }


    override fun getAllProducts(): Flow<NetworkResponseState<List<ProductAll>>> {


        return combine(
            firebaseDataSource.getAllProducts(),
            roomDataSource.getInfoProducts()
        ) { firebase, room ->

            favoriteMap = room.associate { it.id to it.favorite }
            cartMap = room.associate { it.id to it.cart }

            when (firebase) {
                is NetworkResponseState.Error -> NetworkResponseState.Error(firebase.exception)
                NetworkResponseState.Loading -> NetworkResponseState.Loading
                is NetworkResponseState.Success -> {


                    val productAlll = firebase.result.map {

                        val favorite = favoriteMap[it.idp.toLong()]
                        val cart = cartMap[it.idp.toLong()]

                        it.toProductAll(favorite, cart)

                    }

                    NetworkResponseState.Success(productAlll)

                }

            }

        }
    }

    override suspend fun getProductById(productId: String): NetworkResponseState<ProductAll> {

        val result = firebaseDataSource.getProductById(productId)

        return when (result) {
            is NetworkResponseState.Error -> {
                NetworkResponseState.Error(Exception("dd"))
            }

            NetworkResponseState.Loading -> {
                NetworkResponseState.Loading
            }

            is NetworkResponseState.Success -> {

                val a = roomDataSource.getInfoProductById(productId.toLong())

                NetworkResponseState.Success(result.result.toProductAll(a.favorite, a.cart))
            }
        }

    }

}

