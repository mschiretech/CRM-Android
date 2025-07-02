package com.mschiretech.crm_android.navGraph

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.mschiretech.crm_android.splash_and_authentication.Sign_in.Sign_in_view
import com.mschiretech.crm_android.splash_and_authentication.Sign_up.Sign_up_view
import com.mschiretech.crm_android.splash_and_authentication.SplashScreen

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController=navController, startDestination = OnbordingScreens.Splash.route) {
        composable (route = OnbordingScreens.Splash.route){
            SplashScreen(navController)
        }
        composable(route = OnbordingScreens.Sign_in.route) {
            Sign_in_view(navController)
        }
        composable(route = OnbordingScreens.Sign_up.route){
            Sign_up_view(navController)
        }
    }
}