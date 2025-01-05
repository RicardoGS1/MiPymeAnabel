package com.virtualworld.mipymeanabel.data.repository

import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import com.virtualworld.mipymeanabel.data.dto.ProductAll
import com.virtualworld.mipymeanabel.data.dto.ProductInfo
import com.virtualworld.mipymeanabel.data.mapper.toProductAll
import com.virtualworld.mipymeanabel.data.mapper.toProductCart
import com.virtualworld.mipymeanabel.data.source.local.RoomDataSource
import com.virtualworld.mipymeanabel.data.source.remote.FirebaseDataSource
import com.virtualworld.mipymeanabel.domain.models.ProductCart
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImp(
    private val firebaseDataSource: FirebaseDataSource,
    private val roomDataSource: RoomDataSource
) : ProductRepository {

    private var favoriteMap: Map<Long, Boolean> = emptyMap()
    private var cartMap: Map<Long, Boolean> = emptyMap()
    private val productCache = mutableMapOf<Long, Product>()


    override suspend fun changerCart(id: Long) {
        roomDataSource.updateInfoProduct(
            ProductInfo(
                id = id,
                favorite = favoriteMap[id] ?: false,
                cart = !(cartMap[id] ?: false),
            )
        )
    }

    override suspend fun changerFavorite(id: Long) {

        roomDataSource.updateInfoProduct(
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

                        productCache[it.idp.toLong()] = it

                        val favorite = favoriteMap[it.idp.toLong()]
                        val cart = cartMap[it.idp.toLong()]

                        it.toProductAll(favorite, cart)

                    }

                    NetworkResponseState.Success(productAlll)

                }

            }

        }
    }

    override fun getProductById(productId: String): Flow<NetworkResponseState<ProductAll>> =

        flow {

            val result = firebaseDataSource.getProductById(productId)

            when (result) {
                is NetworkResponseState.Error -> {
                    emit(NetworkResponseState.Error(Exception("dd")))
                }

                NetworkResponseState.Loading -> {
                    emit(NetworkResponseState.Loading)
                }

                is NetworkResponseState.Success -> {

                    roomDataSource.getInfoProductById(productId.toLong()).collect {
                        emit(
                            NetworkResponseState.Success(
                                result.result.toProductAll(
                                    it?.favorite ?: false,
                                    it?.cart ?: false
                                )
                            )
                        )
                    }

                }
            }

        }

    override fun getProductCart(): Flow<List<ProductCart>> {

        return roomDataSource.getInfoProducts().map { productInfo ->

             productInfo.filter { it.cart }.mapNotNull {
                 productCache[it.id]?.toProductCart()

                }

            }
        }

    override suspend fun deleteAllCart() {
        roomDataSource.deleteAllCart()
    }


}

