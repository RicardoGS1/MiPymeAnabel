package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.ui.screen.profile.component.ResumeOrder
import com.virtualworld.mipymeanabel.ui.screen.profile.component.sign.SelectSign

@Composable
fun ProfileScreen( viewModel: ProfileViewModel  ) {

    val authState by viewModel.userState.collectAsState()

    val signInState by viewModel.signInState.collectAsState()
    val signUpState by viewModel.signUpState.collectAsState()

    val signUp = {name:String, mail:String, password:String ->   viewModel.signUp(name, mail, password)}
    val signIn = { mail:String, password:String ->   viewModel.singIn(mail, password) }
    val signOut = {viewModel.signOut() }

    val ordersState by viewModel.ordersState.collectAsStateWithLifecycle()



    when(authState){
        is AuthenticationState.Authenticated -> {

            ResumeOrder((authState as AuthenticationState.Authenticated<String>).result ,ordersState,signOut)

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
