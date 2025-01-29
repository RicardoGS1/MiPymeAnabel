package com.virtualworld.mipymeanabel.ui.screen.profile.component.sign

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.virtualworld.mipymeanabel.data.model.SignResponseState
import com.virtualworld.mipymeanabel.ui.screen.profile.component.common.TitleSign
import mipymeanabel.composeapp.generated.resources.Res
import mipymeanabel.composeapp.generated.resources.YoungSerif_Regular
import org.jetbrains.compose.resources.Font


@Composable
fun SelectSign(
    signIn: (String, String) -> Unit,
    signUp: (String, String, String) -> Unit,
    signInState: SignResponseState,
    signUpState: SignResponseState
) {

   // val myFont = fontResource("YoungSerif-Regular.ttf")

    val primaryColor = MaterialTheme.colorScheme.primary
    val darkerPrimaryColor = Color.Black

    var visibleSignInCard by remember { mutableStateOf(false) }
    var visibleSignUpCard by remember { mutableStateOf(false) }


    val changerVisibleSignInCard = {
        visibleSignInCard = false
        visibleSignUpCard = true
    }

    val changerVisibleSignUpCard = {
        visibleSignInCard = true
        visibleSignUpCard = false
    }



    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Brush.horizontalGradient(listOf(primaryColor, darkerPrimaryColor)))
            .clickable(indication = null, // Sin efecto visual
                interactionSource = remember { MutableInteractionSource() })
            {
                visibleSignInCard = false
                visibleSignUpCard = false
            }
    ) {

        TitleSign(visibleSignInCard, "Inicie su sesión")
        TitleSign(visibleSignUpCard, "Cree su cuenta")


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
               // fontWeight = FontWeight.Normal,
                fontFamily = FontFamily(Font(Res.font.YoungSerif_Regular) )

                )

            Text(
                modifier = Modifier.padding(bottom = 40.dp),
                text = "Bienvenido",
                style = MaterialTheme.typography.titleLarge.copy(
                    fontSize = 32.sp, color = Color.White
                ),
               // fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(Res.font.YoungSerif_Regular) )
            )

            Button(
                onClick = { visibleSignUpCard = true },
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
                Text(text = "Crear Cuenta", Modifier.padding(horizontal = 32.dp))

            }


            Button(
                onClick = { visibleSignInCard = true },
                modifier = Modifier
                    .border(
                        width = 1.dp,
                        color = Color.White,
                        shape = RoundedCornerShape(32.dp)
                    ),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                    contentColor = Color.White
                )
            ) {
                Text(text = "Iniciar Sesión", Modifier.padding(horizontal = 32.dp))

            }


        }



        SignInCard(Modifier.align(Alignment.BottomCenter), visibleSignInCard, signIn, signInState, changerVisibleSignInCard)
        SignUpCard(Modifier.align(Alignment.BottomCenter), visibleSignUpCard, signUp, signUpState, changerVisibleSignUpCard)

    }


}




