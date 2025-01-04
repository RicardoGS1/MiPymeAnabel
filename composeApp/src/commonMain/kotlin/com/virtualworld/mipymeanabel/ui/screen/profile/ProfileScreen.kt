package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.ui.screen.profile.component.sign.SelectSign

@Composable
fun ProfileScreen( viewModel: ProfileViewModel  ) {

    val authState by viewModel.userState.collectAsState()

    val signInState by viewModel.signInState.collectAsState()
    val signUpState by viewModel.signUpState.collectAsState()

    val signUp = {name:String, mail:String, password:String ->   viewModel.signUp(name, mail, password)}
    val signIn = { mail:String, password:String ->   viewModel.singIn(mail, password) }



    when(authState){
        is AuthenticationState.Authenticated -> {

            Column(){
            Text("Bienvenido " + (authState as AuthenticationState.Authenticated).user    )

                Button(
                    onClick = {
                        viewModel.signOut()
                        }

                ) {
                    Text(text = "SignOut")
                }
            }

        }
        is AuthenticationState.AuthenticationError -> {

            Text("Error"  + (authState as AuthenticationState.AuthenticationError).error )

        }
        is AuthenticationState.Loading -> {

            Text("Cargando" )

        }
        is AuthenticationState.Unauthenticated -> {

            SelectSign(signIn,signUp,signInState,signUpState)







        }
    }


}
