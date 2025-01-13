package com.virtualworld.mipymeanabel.ui.screen.common

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AlertDialogCommon(
    showDialog: Boolean,
    navProfiler: () -> Unit,
    changerShowDialog: (Boolean) -> Unit
) {


    if (showDialog) {
        AlertDialog(
            onDismissRequest = { changerShowDialog(false)},
            title = { Text("Registro necesario") },
            text = { Text("Para continuar, debes registrarte o iniciar sesión.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        changerShowDialog(false)
                        navProfiler()
                    }
                ) {
                    Text("Registrarse")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { changerShowDialog(false) }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}