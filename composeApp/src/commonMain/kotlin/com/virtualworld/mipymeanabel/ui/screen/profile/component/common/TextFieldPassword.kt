package com.virtualworld.mipymeanabel.ui.screen.profile.component.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun TextFieldPassword(
    password: String,
    changerPassword: (String) -> Unit,
    isPasswordValid: Boolean,
    changerIsPasswordValid: (Boolean) -> Unit
) {

    var passwordVisible by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = {
            changerPassword(it)
            changerIsPasswordValid(true)
        },
        label = { Text("Contrceña", color = MaterialTheme.colorScheme.primary) },
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 16.dp, horizontal = 36.dp),
        colors = TextFieldDefaults.colors(
            disabledContainerColor = Color.Transparent,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent
        ),
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(
                    imageVector = if (passwordVisible) Icons.Filled.Info else Icons.Filled.Lock,
                    contentDescription = if (passwordVisible) "Hide password" else "Show password"
                )
            }
        },
        isError = !isPasswordValid, // Muestra un error si el correo electrónico no es válido
        supportingText = {
            if (!isPasswordValid) {
                Text("Verifique las Contraceñas", color = Color.Red)
            }
        }

    )
}