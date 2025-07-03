package com.mschiretech.crm_android.varifications.otp


fun otp_verification(enteredOTP: String, actualOTP: String): Boolean {
    return enteredOTP == actualOTP
}