package com.mschiretech.crm_android.Onboarding.navGraph

import Sign_up_view
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mschiretech.crm_android.Onboarding.Forgot_password.Forgot_password_view
import com.mschiretech.crm_android.Onboarding.Sign_in.Sign_in_view
import com.mschiretech.crm_android.Onboarding.SplashScreen
import com.mschiretech.crm_android.customer.dashBoard.DashBoard
import com.mschiretech.crm_android.customer.navigation.CustomerNavigation
import com.mschiretech.crm_android.customer.navigation.CustomerScreens

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController=navController, startDestination = OnboardingScreens.Splash.route) {
        composable (route = OnboardingScreens.Splash.route){
            SplashScreen(navController)
        }
        composable(route = OnboardingScreens.Sign_in.route) {
            Sign_in_view(navController)
        }
        composable(route = OnboardingScreens.Sign_up.route){
            Sign_up_view(navController)
        }
        composable(route = OnboardingScreens.Forgot_password.route){
            Forgot_password_view(navController)
        }
        composable(route = CustomerScreens.Dashboard.route) {
            DashBoard(navController)
        }
        //if (role == "customer") {
        //CustomerNavigation(navController)
        // }
    }
}
