package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FirebaseDataSourceImpl() {

    private val firestore = Firebase.firestore

    fun getProducts() : Flow<NetworkResponseState<List<Product>>> = flow {

        try {

            firestore.collection("PRODUCTS").snapshots.collect { querySnapshot ->
                val products = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<Product>()
                }
                println ("getProducts: $products")
                emit(NetworkResponseState.Success(products))
            }
        } catch (e: FirebaseFirestoreException) {
            emit(NetworkResponseState.Error(e))
        }
    }

}