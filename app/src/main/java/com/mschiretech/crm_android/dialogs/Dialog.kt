package com.mschiretech.crm_android.dialogs

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties

@Composable
fun Dialog(showDialog: Boolean, onDismiss: () -> Unit, title:String, message: String) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onDismiss) {
                    Text("OK", color = if (isSystemInDarkTheme()) Color.White
                    else Color.Black)
                }
            },
            title = {Text(title,color = if (isSystemInDarkTheme()) Color.White
            else Color.Black) },
            text = { Text(message, color = if (isSystemInDarkTheme()) Color.White
            else Color.Black)},
            properties = DialogProperties(dismissOnClickOutside = false)
        )
    }
}
