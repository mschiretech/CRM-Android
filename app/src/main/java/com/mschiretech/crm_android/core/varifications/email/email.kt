package com.mschiretech.crm_android.core.varifications.email

import android.util.Patterns


//validation functions
fun isValidEmail(email: String): Boolean {
    return email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()
}