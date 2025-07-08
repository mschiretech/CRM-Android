package com.mschiretech.crm_android.core.model

data class User(
    val id: Int = 0,
    val name: String,
    val username: String,
    val email: String,
    val password: String = ""
)
