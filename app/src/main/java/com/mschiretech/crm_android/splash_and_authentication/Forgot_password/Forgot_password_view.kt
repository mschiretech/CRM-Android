package com.mschiretech.crm_android.splash_and_authentication.Forgot_password

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.splash_and_authentication.Sign_in.Sign_in_view
import com.mschiretech.crm_android.splash_and_authentication.Sign_up.isValidEmail

@Composable
fun Forgot_password_view(
    navController: NavController,
) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .background(
                    if (isSystemInDarkTheme()) Color(0x86020221)
                    else Color(0xFF8690CC)
                )
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ){
            var email by remember { mutableStateOf("") }

            val isEmailValid by remember {
                derivedStateOf { isValidEmail(email) }
            }
        }
    }
}
fun isValidEmail(email: String): Boolean {
    return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

@Preview()
@Composable
fun Forgot_password_ScreenPreview() {
    Forgot_password_view(navController = rememberNavController())
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Forgot_password_ScreenDarkPreview() {
    Forgot_password_view(navController = rememberNavController())
}