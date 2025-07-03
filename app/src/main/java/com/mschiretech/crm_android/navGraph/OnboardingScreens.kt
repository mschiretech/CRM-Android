package com.mschiretech.crm_android.navGraph

sealed class OnboardingScreens(val route : String) {
    object Splash: OnboardingScreens("splash")
    object Sign_in: OnboardingScreens("sign_in")
    object Sign_up: OnboardingScreens("Sign_up")
    object Forgot_password: OnboardingScreens("forgot_password")
    object Otp: OnboardingScreens("otp")
    object Dash_bord: OnboardingScreens("dash_bord")
}