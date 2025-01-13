package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.dto.Order
import com.virtualworld.mipymeanabel.data.dto.OrderProducts
import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow


class FirebaseDataSourceImpl(private val firestore: FirebaseFirestore) : FirebaseDataSource {

    override fun getAllProducts(): Flow<NetworkResponseState<List<Product>>> = flow {
        try {

            firestore.collection("PRODUCTS").snapshots.collect { querySnapshot ->
                val products = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<Product>()
                }
                println("getProducts: $products")
                emit(NetworkResponseState.Success(products))
            }
        } catch (e: FirebaseFirestoreException) {
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

    override suspend fun addOrder(order: Order): NetworkResponseState<Boolean> {


        firestore.collection("orders")
            .document("0FlkOEsgT8RO3wOS5Ot1J73aXxx2").collection("collectionOrders")
            .document.set(order)

        return NetworkResponseState.Success(true)
    }

    override fun getOrders(): Flow<NetworkResponseState<List<Order>>> = flow {

        try{
            firestore.collection("orders")
                .document("0FlkOEsgT8RO3wOS5Ot1J73aXxx2")
                .collection("collectionOrders")
                .snapshots.collect {

                    val orders = it.documents.map { documentSnapshot ->
                        documentSnapshot.data<Order>()
                    }
                    println("getProducts: $orders")
                    emit(NetworkResponseState.Success(orders))
                }
        } catch (e:Exception){
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
}
