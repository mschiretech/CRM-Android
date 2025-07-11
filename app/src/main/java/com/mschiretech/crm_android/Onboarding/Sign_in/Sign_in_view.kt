package com.mschiretech.crm_android.Onboarding.Sign_in

import SocialLoginButton
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.R
import com.mschiretech.crm_android.dialogs.Dialog
import com.mschiretech.crm_android.Onboarding.navGraph.OnboardingScreens
import com.mschiretech.crm_android.core.internet.NetworkState
import com.mschiretech.crm_android.core.internet.observeNetworkState
import com.mschiretech.crm_android.customer.navigation.CustomerScreens
import com.mschiretech.crm_android.dialogs.NoInternetDialog
import com.mschiretech.crm_android.ui.theme.accent
import com.mschiretech.crm_android.ui.theme.borderDark
import com.mschiretech.crm_android.ui.theme.borderLight
import com.mschiretech.crm_android.ui.theme.textDark
import com.mschiretech.crm_android.ui.theme.textLight
import com.mschiretech.crm_android.varifications.userFinder.isUserExist
@Composable
fun Sign_in_view(
    navController: NavController,
    onLoginSuccess: (String) -> Unit = {},
) {
    //Colors
    val isDark = isSystemInDarkTheme()
    val backgroundColor = if (isSystemInDarkTheme()) Brush.linearGradient(
        colors = listOf(Color(0xFF0D0D0D), Color(0xFF5f4B8B), Color(0xFFcbbbf6))
    ) else Brush.linearGradient(colors = listOf(Color(0xff2b2b2b), Color(0xffa593e0), Color(0xffdcd6f7)))
    val textColor = if (isDark) textDark else textLight
    val borderColor = if (isDark) borderDark else borderLight
    val labelColor = borderColor
    val buttonBg = accent
    val buttonText = Color.White
    val iconColor =  if (isSystemInDarkTheme()) Color.White else Color.Black
    val dividerColor = if (isSystemInDarkTheme()) Color.White else Color.Black


    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    //Internet
    val context = LocalContext.current
    val networkState by context.observeNetworkState().collectAsState(initial = NetworkState.Unavailable)
    var showNoInternetDialog by remember { mutableStateOf(true) }

    if (networkState == NetworkState.Lost) showNoInternetDialog = true
    if (networkState == NetworkState.Available) showNoInternetDialog = false

    val isUserExist by remember {
        derivedStateOf { isUserExist(email.trim(), password) }
    }

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(R.drawable.app),
                contentDescription = "App Logo",
                modifier = Modifier.size(120.dp)
            )

            Spacer(Modifier.height(24.dp))

            Text(
                "Welcome Back !!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            )

            Spacer(Modifier.height(8.dp))
            Text("Good to see you again !!", style = MaterialTheme.typography.bodyLarge)

            Spacer(Modifier.height(32.dp))

            // Email Field
            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email", fontStyle = FontStyle.Italic) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(Icons.Default.Email, contentDescription = null, tint = iconColor)
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    cursorColor = borderColor,
                    focusedLabelColor = labelColor,
                    unfocusedLabelColor = labelColor
                )
            )

            Spacer(Modifier.height(8.dp))

            // Password Field
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", fontStyle = FontStyle.Italic) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {
                    Icon(Icons.Default.Lock, contentDescription = null, tint = iconColor)
                },
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Image(
                            painter = painterResource(
                                if (isPasswordVisible) R.drawable.visibility else R.drawable.visibility_off
                            ),
                            contentDescription = "Toggle Visibility",
                            modifier = Modifier.size(24.dp),
                            colorFilter = ColorFilter.tint(iconColor)
                        )
                    }
                },
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    cursorColor = borderColor,
                    focusedLabelColor = labelColor,
                    unfocusedLabelColor = labelColor
                )
            )

            Text(
                "Forgot Password?",
                modifier = Modifier
                    .padding(top = 8.dp, bottom = 16.dp)
                    .align(Alignment.Start)
                    .clickable {
                        navController.navigate(OnboardingScreens.Forgot_password.route)
                    },
                textDecoration = TextDecoration.Underline,
                color = Color.Red,
                style = MaterialTheme.typography.bodyMedium
            )

            if (showNoInternetDialog) {
                NoInternetDialog(showDialog = showNoInternetDialog, onDismissRequest = { showNoInternetDialog = false })
            }

            Dialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                title = "Error",
                message = "Invalid Email or password"
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    if (isUserExist) {
                        navController.navigate(CustomerScreens.Dashboard.route) {
                            popUpTo(0) { inclusive = true }
                        }
                    } else {
                        showDialog = true
                        email = ""
                        password = ""
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = buttonBg),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign In", color = buttonText)
            }

            Spacer(Modifier.height(12.dp))

            // OR Divider
            Row(
                modifier = Modifier.fillMaxWidth().padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Divider(color = dividerColor, modifier = Modifier.weight(1f))
                Text("Or", modifier = Modifier.padding(horizontal = 16.dp))
                Divider(color = dividerColor, modifier = Modifier.weight(1f))
            }

            Spacer(Modifier.height(12.dp))
            Row (
                modifier = Modifier.fillMaxWidth().padding(8.dp),
                horizontalArrangement = Arrangement.Center
            ){
                SocialLoginButton(
                    icon = painterResource(id = R.drawable.google),
                    text = "Google"
                )

                SocialLoginButton(
                    icon = painterResource(id = R.drawable.github),
                    text = "Github"
                )
            }
            Spacer(Modifier.height(24.dp))

            Row {
                Text("Don’t have an account? ", color = textColor)
                Text(
                    "Sign up",
                    modifier = Modifier.clickable {
                        navController.navigate(OnboardingScreens.Sign_up.route)
                    },
                    color = Color.Blue,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SocialLoginButton(icon: Painter, text: String) {
    var showDialog by remember { mutableStateOf(false) }
    OutlinedButton(
        onClick = {
            showDialog = true
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 24.dp),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color.Black,
            containerColor = Color.White
        )
    ) {
//        Dialog(
//            showDialog = showDialog,
//            onDismiss = { showDialog = false },
//            title = "Not Available",
//            message = "This option is not available for mow !!"
//        )
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp)
            )
            Spacer(modifier = Modifier.width(12.dp))
            Text(text)
        }
    }
}


@Preview()
@Composable
fun SplashScreenPreview() {
    Sign_in_view(navController = rememberNavController(),)
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Sign_in_view(navController = rememberNavController(),)
}
