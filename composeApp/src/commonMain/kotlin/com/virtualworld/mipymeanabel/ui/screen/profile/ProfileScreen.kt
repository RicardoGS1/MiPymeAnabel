package com.virtualworld.mipymeanabel.ui.screen.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.virtualworld.mipymeanabel.data.model.AuthenticationState
import com.virtualworld.mipymeanabel.ui.screen.profile.component.ResumeOrder
import com.virtualworld.mipymeanabel.ui.screen.profile.component.sign.SelectSign

@Composable
fun ProfileScreen(viewModel: ProfileViewModel, onOrderClicked: (String) -> Unit) {

    val authState by viewModel.userState.collectAsState()

    val signInState by viewModel.signInState.collectAsState()
    val signUpState by viewModel.signUpState.collectAsState()

    val signUp =
        { name: String, mail: String, password: String -> viewModel.signUp(name, mail, password) }
    val signIn = { mail: String, password: String -> viewModel.singIn(mail, password) }
    val signOut = { viewModel.signOut() }

    val ordersState by viewModel.ordersState.collectAsStateWithLifecycle()



    when (authState) {
        is AuthenticationState.Authenticated -> {

            ResumeOrder(
                (authState as AuthenticationState.Authenticated<String>).result,
                ordersState,
                signOut,
                onOrderClicked
            )

        }

        is AuthenticationState.AuthenticationError -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error" + (authState as AuthenticationState.AuthenticationError).error)
            }
        }

        is AuthenticationState.Loading -> {

            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }

        }

        is AuthenticationState.Unauthenticated -> {

            SelectSign(signIn, signUp, signInState, signUpState)


        }
    }


}
