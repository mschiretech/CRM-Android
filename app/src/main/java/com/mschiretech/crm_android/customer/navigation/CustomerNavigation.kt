package com.mschiretech.crm_android.customer.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mschiretech.crm_android.customer.dashBoard.DashBoard


@Composable
fun CustomerNavigation(navController: NavHostController){
    NavHost(navController=navController, startDestination = CustomerScreens.Dashboard.route) {
        composable(route = CustomerScreens.Dashboard.route) {
            DashBoard(navController)
        }
    }
}