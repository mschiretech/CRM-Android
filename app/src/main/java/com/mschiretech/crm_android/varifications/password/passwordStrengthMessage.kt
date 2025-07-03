package com.mschiretech.crm_android.varifications.password


fun getPasswordStrengthMessage(password: String): String {
    if (password.isEmpty()) return ""

    val issues = mutableListOf<String>()

    if (password.length < 8) issues.add("8+ characters")
    if (!password.any { it.isLowerCase() }) issues.add("lowercase letter")
    if (!password.any { it.isUpperCase() }) issues.add("uppercase letter")
    if (!password.any { it.isDigit() }) issues.add("number")
    if (!password.any { it in "!@#$%^&*()_+-=[]{}|;:,.<>?" }) issues.add("special character")

    return if (issues.isEmpty()) {
        "Strong password!"
    } else {
        "Missing: ${issues.joinToString(", ")}"
    }
}