package com.mschiretech.crm_android.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mschiretech.crm_android.Onboarding.Forgot_password.Forgot_password_view
import com.mschiretech.crm_android.Onboarding.Sign_in.Sign_in_view
import com.mschiretech.crm_android.Onboarding.Sign_up.Sign_up_view
import com.mschiretech.crm_android.Onboarding.SplashScreen

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
    }
}