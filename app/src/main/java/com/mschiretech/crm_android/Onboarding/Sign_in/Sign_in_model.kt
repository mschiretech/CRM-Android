package com.mschiretech.crm_android.Onboarding.Sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class Sign_in_model(): ViewModel() {
    var userName by mutableStateOf(String)
    var password by mutableStateOf(String)
}