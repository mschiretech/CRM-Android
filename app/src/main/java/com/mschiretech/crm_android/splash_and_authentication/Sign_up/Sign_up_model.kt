package com.mschiretech.crm_android.splash_and_authentication.Sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Sign_up_model(): ViewModel() {
    var fullName by mutableStateOf(String)
    var email by mutableStateOf(String)
    var password by mutableStateOf(String)
}