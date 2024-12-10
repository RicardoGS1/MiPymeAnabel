package com.virtualworld.mipymeanabel.data.source.remote

import androidx.lifecycle.get
import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.dto.Product
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take


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

        println("buscando po id")

       return try {

            var result: Product? = null

            firestore.collection("PRODUCTS")
                .where { "idp" equalTo productId }.snapshots.take(1).collect { productDocument ->
                    result = productDocument.documents.firstOrNull()?.data<Product>()
                }

           if (result != null) {
                NetworkResponseState.Success(result!!)
            } else {
                NetworkResponseState.Error(Exception("No se encontro el producto"))
            }

        } catch (e: Exception) {
            NetworkResponseState.Error(e)
        }

    }
}
