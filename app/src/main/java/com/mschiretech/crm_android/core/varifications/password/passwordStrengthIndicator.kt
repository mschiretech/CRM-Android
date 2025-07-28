package com.mschiretech.crm_android.core.varifications.password

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color


@Composable
fun PasswordStrengthIndicator(password: String) {
    val strength = when {
        password.isEmpty() -> 0
        password.length < 8 -> 1
        !password.any { it.isLowerCase() } || !password.any { it.isUpperCase() } -> 2
        !password.any { it.isDigit() } -> 3
        !password.any { it in "!@#$%^&*()_+-=[]{}|;:,.<>?" } -> 4
        else -> 5
    }

    val color = when (strength) {
        0, 1 -> Color.Red
        2, 3 -> Color.Red
        4 -> Color.Yellow
        5 -> Color.Green
        else -> Color.Gray
    }

    val strengthText = when (strength) {
        0 -> ""
        1 -> "Very Weak"
        2 -> "Weak"
        3 -> "Fair"
        4 -> "Good"
        5 -> "Strong"
        else -> ""
    }

    if (password.isNotEmpty()) {
        Text(
            text = strengthText,
            color = color,
            style = MaterialTheme.typography.bodySmall
        )
    }
}