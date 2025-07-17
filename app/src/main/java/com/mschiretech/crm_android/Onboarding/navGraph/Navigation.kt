package com.mschiretech.crm_android.Onboarding.navGraph

import Sign_up_view
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.mschiretech.crm_android.Onboarding.Forgot_password.Forgot_password_view
import com.mschiretech.crm_android.Onboarding.Sign_in.Sign_in_view
import com.mschiretech.crm_android.Onboarding.SplashScreen

fun NavGraphBuilder.authGraph(navController: NavController) {
       navigation(
            startDestination = OnboardingScreens.Splash.route,
            route = OnboardingScreens.AuthRoute.route
        ) {
            composable(route = OnboardingScreens.Splash.route) {
                SplashScreen(navController)
            }
            composable(route = OnboardingScreens.Sign_in.route) {
                Sign_in_view(navController)
            }
            composable(route = OnboardingScreens.Sign_up.route) {
                Sign_up_view(navController)
            }
            composable(route = OnboardingScreens.Forgot_password.route) {
                Forgot_password_view(navController)
            }
        }
    }

