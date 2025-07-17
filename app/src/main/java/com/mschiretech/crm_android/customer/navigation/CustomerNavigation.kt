package com.mschiretech.crm_android.customer.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mschiretech.crm_android.customer.account.Account
import com.mschiretech.crm_android.customer.dashBoard.DashBoard
import com.mschiretech.crm_android.customer.dashBoard.drawerItems.Product


fun NavGraphBuilder.CustomerGraph(navController: NavController) {
    navigation(
        startDestination = CustomerScreens.Dashboard.route,
        route = CustomerScreens.CustomerRoute.route
    ) {
        composable(route = CustomerScreens.Dashboard.route) {
            DashBoard(navController)
        }
        composable(route = CustomerScreens.Account.route) {
            Account(navController)
        }
        composable(route = CustomerScreens.Product.route) {
            Product(navController)
        }
    }
}
