package com.virtualworld.mipymeanabel.data.source.remote



import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.Serializable

@Serializable
data class Productss (
    val price: String,

)

class FirebaseDataSourceImpl() {



    private val firestore = Firebase.firestore

    fun getUsers() = flow {


            try {
                firestore.collection("PRODUCTS").snapshots.collect { querySnapshot ->
                    val users = querySnapshot.documents.map { documentSnapshot ->
                        documentSnapshot.data<Productss>()
                    }
                    emit(users)
                }
            }catch (e: Exception) {
                println(e)
            }


    }






//
//    suspend fun getAllProducts(userId: String): NetworkResponseState<List<Products>> =
//        withContext(Dispatchers.IO) {
//            try {
//                // Obtener la colección de Firestore
//                val collectionRef = firebaseFirestore
//                    .collection("fire-agenda-venta")
//                    .document(userId)
//                    .collection("productRoomList")
//
//                // Obtener los documentos
//                val querySnapshot = collectionRef.get().await()
//
//                val liProductRoom = querySnapshot.documents.map { document ->
//                    ProductRoom(
//                        id = document.getLong("id") ?: 0,
//                        nombre = document.getString("nombre") ?: "",
//                        compra = document.getDouble("compra")?.toFloat() ?: 0f,
//                        venta1 = document.getDouble("venta1")?.toFloat() ?: 0f,
//                        venta2 = document.getDouble("venta2")?.toFloat() ?: 0f,
//                        venta3 = document.getDouble("venta3")?.toFloat() ?: 0f,
//                        venta4 = document.getDouble("venta4") ?.toFloat() ?: 0f,
//                        venta5 = document.getDouble("venta5") ?.toFloat() ?: 0f,
//                    )
//                }
//
//                println(liProductRoom)
//
//                NetworkResponseState.Success(liProductRoom)
//            } catch (e: Exception) {
//                NetworkResponseState.Error(e)
//            }
//        }


}