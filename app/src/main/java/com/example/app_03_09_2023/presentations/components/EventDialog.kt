package com.example.app_03_09_2023.presentations.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun EventDialog(
    modifier: Modifier = Modifier,
    onDismiss: (() -> Unit)? = null,
    @StringRes errorMessage: Int,
) {
    AlertDialog(
        modifier = Modifier
            .background(Color.White)
            .padding(16.dp),
        onDismissRequest = { /*TODO*/ },
        title = {
            Text(
                text = "Error",
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                ) //fin de estilo
            ) //fin de estilo
        }, //fin del tittle
        text = {
            Text(
                text = LocalContext.current.getString(errorMessage),
                style = TextStyle(
                    color = MaterialTheme.colors.onSurface,
                    fontSize = 16.sp
                ) //fin de estilo
            ) //fin Text
        }, //fin text
        buttons = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.End
            ) //fin row
            {
                TextButton(onClick = { onDismiss?.invoke() }) {
                    Text(
                        text = "Aceptar",
                        style = MaterialTheme.typography.button

                    )
                }
            }
        }
    )
}