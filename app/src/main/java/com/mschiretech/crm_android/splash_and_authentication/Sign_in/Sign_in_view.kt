package com.mschiretech.crm_android.splash_and_authentication.Sign_in

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.mschiretech.crm_android.navGraph.OnbordingScreens

@Composable
fun Sign_in_view(
    navController: NavController,
    // viewModel: SignInViewModel
) {
    var userName by remember { mutableStateOf("") }
    var isNotValidName by remember { mutableStateOf(false) }
    var password by remember { mutableStateOf("") }
    var isNotValidPassword by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

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
        ) {
            // App logo
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
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Good to see you again !!",
                style = MaterialTheme.typography.bodyLarge
            )
            Spacer(modifier = Modifier.height(32.dp))

            //UserName Test Field
            OutlinedTextField(
                value = userName,
                onValueChange = { userName = it },
                label = { Text("Username", fontStyle = FontStyle.Italic) },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                leadingIcon = {Icon(Icons.Default.Person, contentDescription = "Password Icon",tint = Color.Black)},
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                isError = isNotValidName
            )

            Spacer(Modifier.height(8.dp))

            //Password Text Filled
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password", fontStyle = FontStyle.Italic) },
                modifier = Modifier.fillMaxWidth(),
                leadingIcon = {Icon(Icons.Default.Lock, contentDescription = "Password Icon",tint = Color.Black)},
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(), // Show/hide password
                trailingIcon = {
                    IconButton(onClick = {
                        isPasswordVisible = !isPasswordVisible
                    }) {
                        Image(
                            painter = painterResource(id = if (isPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
                            contentDescription = "Toggle Password Visibility",
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },

                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Gray,
                    unfocusedBorderColor = Color.Black,
                    cursorColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black
                ),
                isError = isNotValidPassword
            )
            Text(
                "Forgot Password?",
                style = MaterialTheme.typography.bodyMedium,
                textDecoration = TextDecoration.Underline,
                color = Color(0xFFEF5A5A),
                modifier = Modifier
                    .padding(top = 8.dp)
                    .align(Alignment.Start)
                    .padding(bottom = 16.dp)
                    .clickable {
                        // TODO: Navigate to forgot password screen
                        // navController.navigate("forgot_password_screen")
                    }
            )
            Spacer(Modifier.height(24.dp))
            Button(
                onClick = {
                    // TODO: Call viewModel.signIn(userName, password)
                },
                colors = ButtonDefaults.buttonColors(Color.Black),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign In")
            }

            Spacer(modifier = Modifier.height(12.dp))
           Row(
               modifier = Modifier.fillMaxWidth().width(24.dp).padding( 16.dp),
               verticalAlignment = Alignment.CenterVertically
           ){
               Divider(
                   color = Color.Black,
                   modifier = Modifier.weight(1f)
               )
               Text(
                   "Or",
                   style = MaterialTheme.typography.bodyMedium,
                   modifier = Modifier.padding(horizontal = 16.dp)
               )
               Divider(
                   color = Color.Black,
                   modifier = Modifier.weight(1f)
               )
           }
            Spacer(modifier = Modifier.height(12.dp))

            SocialLoginButton(
                icon = painterResource(id = R.drawable.google),
                text = "Continue with Google"
            )

            Spacer(modifier = Modifier.height(12.dp))

            SocialLoginButton(
                icon = painterResource(id = R.drawable.github),
                text = "Continue with Github"
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row {
                Text("Don’t have an account? ", color = Color.Black)
                Text(
                    "Sign up",
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.navigate(OnbordingScreens.Sign_up.route)
                    }
                )
            }
        }
    }
}

@Composable
fun SocialLoginButton(icon: Painter, text: String) {
    OutlinedButton(
        onClick = { /* Handle login */ },
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
    Sign_in_view(navController = rememberNavController())
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun SplashScreenDarkPreview() {
    Sign_in_view(navController = rememberNavController())
}
