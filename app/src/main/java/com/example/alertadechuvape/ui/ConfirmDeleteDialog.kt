package com.example.alertadechuvape.ui

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun ConfirmDeleteDialog(

    onDismiss: () -> Unit,

    onConfirm: () -> Unit

) {

    AlertDialog(

        onDismissRequest = onDismiss,

        title = {

            Text("Excluir ocorrência")

        },

        text = {

            Text(
                "Tem certeza que deseja excluir esta ocorrência?\n\nEssa ação não poderá ser desfeita."
            )

        },

        confirmButton = {

            Button(

                onClick = onConfirm

            ) {

                Text("Excluir")

            }

        },

        dismissButton = {

            TextButton(

                onClick = onDismiss

            ) {

                Text("Cancelar")

            }

        }

    )

}