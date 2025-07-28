package com.mschiretech.crm_android.core.varifications.userFinder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

fun FindTheRole(): String{
    val User by mutableStateOf("User")
    if (User == "User" ){
       return User
    } else {
        //This Will Be Admin
        return User
    }
}