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
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.virtualworld.mipymeanabel.data.model.SignResponseState
import com.virtualworld.mipymeanabel.ui.screen.profile.component.common.TextFieldMail
import com.virtualworld.mipymeanabel.ui.screen.profile.component.common.TextFieldPassword
import com.virtualworld.mipymeanabel.ui.screen.profile.component.common.isValidEmail


@Composable
fun SignUpCard(
    modifier: Modifier,
    visibleSignInCard: Boolean,
    signUp: (String, String, String) -> Unit,
    signUpState: SignResponseState,
    changerVisibleSignUpCard: () -> Unit,
) {

    var name: String by remember { mutableStateOf("") }
    var isNameValid by remember { mutableStateOf(true) }

    var mail: String by remember { mutableStateOf("") }
    val changerMail = { updateMail: String -> mail = updateMail }
    var isEmailValid by remember { mutableStateOf(true) }
    val changerIsEmailValid = { updateIsEmailValid: Boolean -> isEmailValid = updateIsEmailValid }

    var password: String by remember { mutableStateOf("") }
    val changerPassword = { updatePassword: String -> password = updatePassword }
    var isPasswordValid by remember { mutableStateOf(true) }
    val changerIsPasswordValid =
        { updateIsPasswordValid: Boolean -> isPasswordValid = updateIsPasswordValid }

    var confirmPassword: String by remember { mutableStateOf("") }
    val confirmChangerPassword = { updatePassword: String -> confirmPassword = updatePassword }


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

                TextField(
                    value = name,
                    onValueChange = {
                        name = it
                        isNameValid = true
                    },
                    label = { Text("Nombre", color = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 36.dp),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        errorContainerColor = Color.Transparent
                    ),
                    isError = !isNameValid, // Muestra un error si el correo electrónico no es válido
                    supportingText = {
                        if (!isNameValid) {
                            Text("Nombre inválido", color = Color.Red)
                        }
                    }


                )

                TextFieldMail(mail, isEmailValid, changerMail, changerIsEmailValid)

                TextFieldPassword(
                    password,
                    changerPassword,
                    isPasswordValid,
                    changerIsPasswordValid
                )

                TextFieldPassword(
                    confirmPassword,
                    confirmChangerPassword,
                    isPasswordValid,
                    changerIsPasswordValid
                )





                Button(
                    onClick = {

                        if (mail.isNotBlank() && isValidEmail(mail)) {


                            if (password.isNotBlank() && password == confirmPassword && password.length > 5) {

                                if (name.isNotBlank()) {
                                    signUp(name, mail, password)
                                } else {
                                    isNameValid = false
                                }

                            } else {
                                isPasswordValid = false
                                // isConfirmPasswordValid = false
                            }
                        } else {
                            isEmailValid = false
                        }


                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 42.dp)
                        .padding(horizontal = 42.dp)


                ) {
                    Text(text = "Crear Cuenta", fontSize = 22.sp)
                }

                if (signUpState is SignResponseState.Error) {
                    Text(
                        text = "Ocurrio un error:   " + signUpState.exception.message.toString(),
                        fontSize = 16.sp,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(horizontal = 32.dp).padding(top = 16.dp)
                    )
                }

                if (signUpState is SignResponseState.Loading) {
                    CircularProgressIndicator(modifier = Modifier.size(42.dp).padding(top = 12.dp))
                }


            }

            Column(
                modifier = Modifier.align(Alignment.BottomEnd).padding(36.dp),
                horizontalAlignment = Alignment.End
            ) {

                Text(text = "Ya tienes una cuenta?")


                TextButton(
                    onClick = {changerVisibleSignUpCard()},
                ) {
                    Text("Iniciar Sesión")
                }
            }
        }

    }
}


