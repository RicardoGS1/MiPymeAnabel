package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.FirebaseUser
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen() {

    val scope = rememberCoroutineScope()
    val auth = remember { Firebase.auth }
    var firebaseUser: FirebaseUser? by remember { mutableStateOf(null) }
    var userMail: String by remember { mutableStateOf("") }
    var userPassword: String by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        firebaseUser = auth.currentUser
    }


    if (firebaseUser == null) {


        Column {
            TextField(
                value = userMail,
                onValueChange = { userMail = it },
                label = { Text("Email") }

            )
            TextField(
                value = userPassword,
                onValueChange = { userPassword = it },
                label = { Text("Email") }

            )

            Button(
                onClick = {
                    scope.launch {
                        try {
                            val result = auth.createUserWithEmailAndPassword(userMail, userPassword)

                            result.user?.sendEmailVerification()


                        } catch (e: Exception) {
                            auth.signInWithEmailAndPassword(userMail, userPassword)


                        }


                        firebaseUser = auth.currentUser

                    }
                }

            ) {
                Text(text = "SignIn")
            }


        }


    } else {

        Column() {
            val isEmailVerified by remember {
                derivedStateOf { firebaseUser?.isEmailVerified == true }
            }

            if (!isEmailVerified) {
                LaunchedEffect(auth.currentUser) {
                    while (!isEmailVerified) {
                        delay(2000)
                        auth.currentUser?.reload()
                        firebaseUser = auth.currentUser
                    }
                }

                Text(text = "Verifica tu correo")
            } else {
                Text(text = auth.currentUser?.isEmailVerified.toString())
                Text(text = firebaseUser?.uid ?: "desconocido")

                Button(
                    onClick = {
                        scope.launch {
                            auth.signOut()
                            firebaseUser = auth.currentUser
                        }
                    }
                ) {
                    Text(text = "SignOut")
                }
            }
        }

    }


}