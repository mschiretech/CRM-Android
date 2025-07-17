package com.mschiretech.crm_android.Onboarding.navGraph

sealed class OnboardingScreens(val route : String) {
    object AuthRoute : OnboardingScreens("auth")
    object Splash: OnboardingScreens("splash")
    object Sign_in: OnboardingScreens("sign_in")
    object Sign_up: OnboardingScreens("Sign_up")
    object Forgot_password: OnboardingScreens("forgot_password")
}