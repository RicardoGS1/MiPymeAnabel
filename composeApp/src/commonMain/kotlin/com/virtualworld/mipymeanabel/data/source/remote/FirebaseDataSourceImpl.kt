package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.model.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
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
                .where { "idp" equalTo productId }
                .snapshots
                .firstOrNull()
                ?.documents
                ?.firstOrNull()
                ?.data<Product>()

            product?.let { NetworkResponseState.Success(it) }
                ?: NetworkResponseState.Error(Exception("No se encontro el producto"))
        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }
    }
}
