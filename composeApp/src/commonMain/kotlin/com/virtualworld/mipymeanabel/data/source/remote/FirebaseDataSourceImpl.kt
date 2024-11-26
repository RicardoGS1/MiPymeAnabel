package com.virtualworld.mipymeanabel.data.source.remote

import com.virtualworld.mipymeanabel.data.model.Product
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.flow


class FirebaseDataSourceImpl() {


    private val firestore = Firebase.firestore

    fun getUsers() = flow {

        try {

            firestore.collection("PRODUCTS").snapshots.collect { querySnapshot ->
                val users = querySnapshot.documents.map { documentSnapshot ->
                    documentSnapshot.data<Product>()
                }
                emit(users)
            }
        } catch (e: Exception) {
            println("maaaal" + e)
        }

    }


}