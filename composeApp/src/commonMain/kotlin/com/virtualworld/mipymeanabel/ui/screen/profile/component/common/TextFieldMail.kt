package com.virtualworld.mipymeanabel.ui.screen.profile.component.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun TextFieldMail(
    mail: String,
    isEmailValid: Boolean,
    changerMail: (String) -> Unit,
    changerIsEmailValid: (Boolean) -> Unit
) {



    TextField(
        value = mail,
        onValueChange = {
            changerMail(it)
            changerIsEmailValid(true)
        },
        label = { Text("Email", color = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 36.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        ),
        isError = !isEmailValid, // Muestra un error si el correo electrónico no es válido
        supportingText = {
            if (!isEmailValid) {
                Text("Correo electrónico inválido", color = Color.Red)
            }
        }

    )


}

fun isValidEmail(email: String): Boolean {
    return email.contains("@") && email.contains(".")
}