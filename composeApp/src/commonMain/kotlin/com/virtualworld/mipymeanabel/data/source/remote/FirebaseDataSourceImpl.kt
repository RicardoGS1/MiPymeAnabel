package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.withTimeoutOrNull


class FirebaseDataSourceImpl(private val firestore: FirebaseFirestore) : FirebaseDataSource {

    class ProductEmptyException : Exception("Product Empty")

    override fun getAllProducts(): Flow<NetworkResponseState<List<Product>>> = flow {
        try {

            emit(NetworkResponseState.Loading)

            firestore.collection("PRODUCTS").snapshots.collect { querySnapshot ->

                val products = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<Product>()
                }
                if (products.isEmpty()) {
                    //throw ProductEmptyException()
                    emit(NetworkResponseState.Error(Exception("Product Empty")))
                } else {
                    emit(NetworkResponseState.Success(products))
                }
            }

        } catch (e: Exception) {
            emit(NetworkResponseState.Error(e))
        }
    }

    override suspend fun getProductById(productId: String): NetworkResponseState<Product> {

        return try {
            val product = firestore.collection("PRODUCTS")
                .where { "idp" equalTo productId }.snapshots.firstOrNull()?.documents?.firstOrNull()
                ?.data<Product>()

            product?.let { NetworkResponseState.Success(it) } ?: NetworkResponseState.Error(
                Exception("No se encontro el producto")
            )
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override suspend fun addOrder(order: Order, uid: String): NetworkResponseState<Boolean> {


        firestore.collection("orders")
            .document(uid).collection("collectionOrders")
            .document(order.number).set(order)

        return NetworkResponseState.Success(true)
    }

    override fun getOrders(uid: String?): Flow<NetworkResponseState<List<Order>>> = flow {

        try {
            if (uid != null) {
                firestore.collection("orders")
                    .document(uid)
                    .collection("collectionOrders")
                    .snapshots.collect {

                        val orders = it.documents.map { documentSnapshot ->
                            documentSnapshot.data<Order>()
                                .copy(listOrderProducts = emptyList()) // hay que separar la lista de de la orden proximamente
                        }
                        emit(NetworkResponseState.Success(orders))
                    }
            } else {
                emit(NetworkResponseState.Success(emptyList()))
            }
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }

    override fun getAllBanels(): Flow<NetworkResponseState<List<String>>> = flow {

        try {

            emit(NetworkResponseState.Loading)

            firestore.collection("BANEL").snapshots.collect { querySnapshot ->

                val banel = querySnapshot.documents.mapNotNull { documentSnapshot ->
                    documentSnapshot.get("image") as? String
                }
                emit(NetworkResponseState.Success(banel))
            }
        } catch (e: FirebaseFirestoreException) {
            emit(NetworkResponseState.Error(e))
        }
    }

    override fun getOrderById(uid: String, orderId: String): Flow<NetworkResponseState<Order>> =
        flow {

            try {

                firestore.collection("orders")
                    .document(uid)
                    .collection("collectionOrders")
                    .document(orderId)
                    .snapshots.collect {

                        val orders = it.data<Order>()

                        emit(NetworkResponseState.Success(orders))

                    }
            } catch (e: Exception) {
                NetworkResponseState.Error(e)
            }

        }


}
