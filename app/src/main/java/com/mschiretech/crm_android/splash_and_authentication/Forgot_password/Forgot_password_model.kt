package com.mschiretech.crm_android.splash_and_authentication.Forgot_password

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Forgot_password_model : ViewModel(){
    var email by mutableStateOf(String)
}