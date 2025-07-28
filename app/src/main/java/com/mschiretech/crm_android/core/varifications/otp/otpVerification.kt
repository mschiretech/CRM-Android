package com.mschiretech.crm_android.core.varifications.otp


fun otp_verification(enteredOTP: String, actualOTP: String): Boolean {
    return enteredOTP == actualOTP
}