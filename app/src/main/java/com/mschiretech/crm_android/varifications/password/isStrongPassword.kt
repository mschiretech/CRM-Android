package com.mschiretech.crm_android.varifications.password

fun isStrongPassword(password: String): Boolean {
    // Check minimum length
    if (password.length < 8) return false

    // Check for at least one lowercase letter
    val hasLowercase = password.any { it.isLowerCase() }

    // Check for at least one uppercase letter
    val hasUppercase = password.any { it.isUpperCase() }

    // Check for at least one digit
    val hasDigit = password.any { it.isDigit() }

    // Check for at least one special character
    val specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?"
    val hasSpecialChar = password.any { it in specialChars }

    return hasLowercase && hasUppercase && hasDigit && hasSpecialChar
}
