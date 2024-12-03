package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.NetworkResponseState
import com.virtualworld.mipymeanabel.data.model.Product
import dev.gitlive.firebase.firestore.FirebaseFirestore
import dev.gitlive.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class FirebaseDataSourceImpl(private val firestore: FirebaseFirestore) : FirebaseDataSource {


  override  fun getAllProducts() : Flow<NetworkResponseState<List<Product>>> = flow {

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