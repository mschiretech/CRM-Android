package com.mschiretech.crm_android.splash_and_authentication

sealed class OnbordingScreens(val route : String) {
    object Splash: OnbordingScreens("splash")
    object Sign_in: OnbordingScreens("sign_in")
    object Sign_up: OnbordingScreens("Sign_up")
    object Forgot_Password: OnbordingScreens("forgot_password")
    object Otp: OnbordingScreens("otp")
    object Dash_bord: OnbordingScreens("dash_bord")
}