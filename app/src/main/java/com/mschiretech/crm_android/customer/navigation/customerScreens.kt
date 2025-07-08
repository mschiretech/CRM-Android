package com.mschiretech.crm_android.customer.navigation

sealed class CustomerScreens (val route : String){
    object Dashboard : CustomerScreens("dashboard")
    object Account : CustomerScreens("account")
}