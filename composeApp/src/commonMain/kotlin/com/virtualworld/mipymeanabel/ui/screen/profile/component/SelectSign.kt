package com.virtualworld.mipymeanabel.ui.screen.profile.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SelectSign(signIn: (String, String) -> Unit) {


    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black // O usa una función para oscurecer primary

    var visibleSignInCard by remember { mutableStateOf(false) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(listOf(primaryColor, darkerPrimaryColor)))
            .clickable(indication = null, // Sin efecto visual
                interactionSource = remember { MutableInteractionSource() })
            { visibleSignInCard = false }
    ) {

        if (visibleSignInCard) {
            Column(modifier = Modifier.padding(24.dp).padding(top = 32.dp)) {
                Text(
                    text = "Hola",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    fontSize = 32.sp
                )
                Text(
                    text = "Inicia Secion!",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White
                )
            }
        }


        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,

            ) {

            Icon(
                modifier = Modifier.size(64.dp),
                imageVector = Icons.Filled.ShoppingCart,
                contentDescription = "Carrito de compras",
                tint = Color.White
            )

            Text(
                modifier = Modifier.padding(bottom = 120.dp),
                text = "MiPyme Anabel",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 32.sp, color = Color.White
                ),
                fontWeight = FontWeight.Normal,

                )

            Text(
                modifier = Modifier.padding(bottom = 40.dp),
                text = "Bienvenido",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 32.sp, color = Color.White
                ),
                fontWeight = FontWeight.Bold,
            )

            Button(
                onClick = {},
                modifier = Modifier
                    .padding(16.dp)
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(32.dp)
                    ), // Borde blanco
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Fondo transparente
                    contentColor = Color.White // Color del texto (opcional)
                )
            ) {
                Text(text = "Crear Usuario", Modifier.padding(horizontal = 32.dp))

            }


            Button(
                onClick = { visibleSignInCard = true },
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(32.dp)
                    ), // Borde blanco
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent, // Fondo transparente
                    contentColor = Color.White // Color del texto (opcional)
                )
            ) {
                Text(text = "Iniciar Secion", Modifier.padding(horizontal = 32.dp))

            }


        }



        SignInCard(Modifier.align(Alignment.BottomCenter), visibleSignInCard, signIn)

    }


}


@Composable
fun SignInCard(modifier: Modifier, visible: Boolean, signIn: (String, String) -> Unit) {

    var userMail: String by remember { mutableStateOf("") }
    var userPassword: String by remember { mutableStateOf("") }

    if (visible) {

        Box(
            modifier = modifier.fillMaxSize().padding(top = 180.dp)
                .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
                .background(MaterialTheme.colorScheme.surface)
        ) {

            Column(
                modifier = Modifier.fillMaxSize().padding(top = 32.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextField(
                    value = userMail,
                    onValueChange = { userMail = it },
                    label = { Text("Email", color = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 36.dp),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        )

                )
                TextField(
                    value = userPassword,
                    onValueChange = { userPassword = it },
                    label = { Text("Contrceña", color = MaterialTheme.colorScheme.primary) },
                    modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 16.dp, horizontal = 36.dp),
                    colors = TextFieldDefaults.colors(
                        disabledContainerColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,

                        )

                )

                TextButton(
                    onClick = {}, modifier = Modifier.align(Alignment.End).padding(end = 36.dp)
                ) {
                    Text("Olvide la contraceña")
                }


                Button(
                    onClick = {

                        signIn(userMail, userPassword)

                    },
                    modifier = Modifier.fillMaxWidth().padding(top = 42.dp)
                        .padding(horizontal = 42.dp)


                ) {
                    Text(text = "SignIn", fontSize = 22.sp)
                }


            }

            Column(
                modifier = Modifier.align(Alignment.BottomEnd).padding(36.dp),
                horizontalAlignment = Alignment.End
            ) {

                Text(text = "No tienes una cuenta?")

                TextButton(
                    onClick = {},

                    ) {
                    Text("Crear Cuenta")
                }
            }
        }

    }


}