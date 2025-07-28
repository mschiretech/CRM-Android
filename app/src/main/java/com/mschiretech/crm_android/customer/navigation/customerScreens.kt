package com.mschiretech.crm_android.customer.navigation

sealed class CustomerScreens (val route : String){
    object CustomerRoute : CustomerScreens("customer")
    object Dashboard : CustomerScreens("dashboard")
    object Account : CustomerScreens("account")
    object Product : CustomerScreens("product")
    object Support : CustomerScreens("support")
    object Industries : CustomerScreens("industries")
}