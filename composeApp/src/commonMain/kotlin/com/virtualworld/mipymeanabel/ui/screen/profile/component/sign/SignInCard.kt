package com.virtualworld.mipymeanabel.ui.screen.profile.component.sign

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.virtualworld.mipymeanabel.data.model.SignResponseState
import com.virtualworld.mipymeanabel.ui.screen.profile.component.common.TextFieldMail
import com.virtualworld.mipymeanabel.ui.screen.profile.component.common.TextFieldPassword
import com.virtualworld.mipymeanabel.ui.screen.profile.component.common.isValidEmail


@Composable
fun SignInCard(
    modifier: Modifier,
    visibleSignInCard: Boolean,
    signIn: (String, String) -> Unit,
    signInState: SignResponseState,
    changerVisibleSignInCard: () -> Unit
) {

    var mail: String by remember { mutableStateOf("") }
    val changerMail = { updateMail: String -> mail = updateMail }
    var isEmailValid by remember { mutableStateOf(true) }
    val changerIsEmailValid = { updateIsEmailValid: Boolean -> isEmailValid = updateIsEmailValid }

    var password: String by remember { mutableStateOf("") }
    val changerPassword = { updatePassword: String -> password = updatePassword }
    var isPasswordValid by remember { mutableStateOf(true) }
    val changerIsPasswordValid =
        { updateIsPasswordValid: Boolean -> isPasswordValid = updateIsPasswordValid }





    AnimatedVisibility(
        visible = visibleSignInCard,
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight }

    ) {


        Box(
            modifier = modifier.fillMaxSize().padding(top = 180.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.surface)
                .clickable(enabled = false) { }
        ) {

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 32.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                TextFieldMail(mail, isEmailValid, changerMail, changerIsEmailValid)

                TextFieldPassword(
                    password,
                    changerPassword,
                    isPasswordValid,
                    changerIsPasswordValid
                )

                TextButton(
                    onClick = {}, modifier = Modifier.align(Alignment.End).padding(end = 36.dp)
                ) {
                    Text("Olvide la contraceña")
                }


                Button(
                    onClick = {

                        if (mail.isNotBlank() && isValidEmail(mail)) {
                            if (password.isNotBlank()) {
                                signIn(mail, password)
                            } else {
                                isPasswordValid = false
                            }

                        } else {
                            isEmailValid = false
                        }


                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 42.dp)
                        .padding(horizontal = 42.dp)


                ) {
                    Text(text = "SignIn", fontSize = 22.sp)
                }

                if (signInState is SignResponseState.Error) {
                    Text(
                        text = "Ocurrio un error:   " + signInState.exception.message.toString(),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(horizontal = 32.dp).padding(top = 16.dp)
                    )
                }

                if (signInState is SignResponseState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(42.dp).padding(top = 12.dp))
                }


            }

            Column(
                modifier = Modifier.align(Alignment.BottomEnd).padding(36.dp),
                horizontalAlignment = Alignment.End
            ) {

                Text(text = "No tienes una cuenta?")

                TextButton(
                    onClick = {changerVisibleSignInCard()},

                    ) {
                    Text("Crear Cuenta")
                }
            }
        }

    }
}




