package com.mschiretech.crm_android.Onboarding.Sign_up

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
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalConfiguration
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
import com.mschiretech.crm_android.navGraph.OnboardingScreens
import com.mschiretech.crm_android.varifications.email.isValidEmail
import com.mschiretech.crm_android.varifications.password.getPasswordStrengthMessage
import com.mschiretech.crm_android.varifications.password.isStrongPassword
import com.mschiretech.crm_android.varifications.userFinder.isNewUserExist

@Composable
fun Sign_up_view(
    navController: NavController,
    // viewModel: SignUpViewModel
) {
    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }

    // Track if fields have been touched for better UX
    var isFullNameTouched by remember { mutableStateOf(false) }
    var isEmailTouched by remember { mutableStateOf(false) }
    var isPasswordTouched by remember { mutableStateOf(false) }
    var isConfirmPasswordTouched by remember { mutableStateOf(false) }

    var showDialog by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    // Derived states for validation
    val isEmailValid by remember {
        derivedStateOf { isValidEmail(email) }
    }

    val isPasswordStrong by remember {
        derivedStateOf { isStrongPassword(password) }
    }

    val isPasswordMatch by remember {
        derivedStateOf { password == confirmPassword && confirmPassword.isNotEmpty() }
    }

    val isFullNameValid by remember {
        derivedStateOf { fullName.trim().length >= 2 }
    }
    val isUserExist by remember {
        derivedStateOf { !isNewUserExist(fullName, email) }
    }

    val isFormValid by remember {
        derivedStateOf {
            isFullNameValid &&
                    isEmailValid &&
                    isPasswordStrong &&
                    isPasswordMatch
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    if (isSystemInDarkTheme()) Color(0x86020221)
                    else Color(0xFF8690CC)
                )
                .then(
                    if (isLandscape) Modifier.verticalScroll(scrollState)
                    else Modifier
                )

                .padding(paddingValues)
                .padding(horizontal = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // App logo
            Image(
                painter = painterResource(R.drawable.app),
                contentDescription = "App Logo",
                modifier = Modifier.size(100.dp)
            )
            Spacer(Modifier.height(12.dp))
            Text(
                "Welcome to MSC HireTech!!",
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontStyle = FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Card(
                elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
                colors = CardDefaults.cardColors(
                    if (isSystemInDarkTheme()) Color(0xFC171736)
                    else Color(0xF7919DE7)
                ),
                shape = RoundedCornerShape(24.dp)
//                modifier = Modifier.padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Full Name text field
                    OutlinedTextField(
                        value = fullName,
                        onValueChange = {
                            fullName = it
                            if (!isFullNameTouched) isFullNameTouched = true
                        },
                        label = { Text("Full Name*", fontStyle = FontStyle.Italic) },
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        leadingIcon = {
                            Icon(
                                Icons.Default.Person,
                                contentDescription = "Full Name",
                                tint = if (isSystemInDarkTheme()) Color.White
                                else Color.Black
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            cursorColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            focusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                        ),
                        //Show error only if touched and invalid
                        isError = isFullNameTouched && !isFullNameValid,
                        supportingText = if (isFullNameTouched && !isFullNameValid) {
                            {
                                Text(
                                    "Full name must be at least 2 characters",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        } else null
                    )
                    //Email text field
                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                            if (!isEmailTouched) isEmailTouched = true
                        },
                        label = { Text("Email*", fontStyle = FontStyle.Italic) },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Email,
                                contentDescription = "Email Icon",
                                tint = if (isSystemInDarkTheme()) Color.White
                                else Color.Black,
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            cursorColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            focusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                        ),
                        //Show error only if touched and invalid
                        isError = isEmailTouched && email.isNotEmpty() && !isEmailValid,
                        supportingText = if (isEmailTouched && email.isNotEmpty() && !isEmailValid) {
                            {
                                Text(
                                    "Please enter a valid email address",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        } else null
                    )
                    // Password Text Field
                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                            if (!isPasswordTouched) isPasswordTouched = true
                        },
                        label = { Text("Password*", fontStyle = FontStyle.Italic) },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Password Icon",
                                tint = if (isSystemInDarkTheme()) Color.White
                                else Color.Black,
                            )
                        },
                        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = {
                                isPasswordVisible = !isPasswordVisible
                            }) {
                                Image(
                                    painter = painterResource(id = if (isPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
                                    contentDescription = "Toggle Password Visibility",
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = ColorFilter.tint(
                                        if (isSystemInDarkTheme()) Color.White
                                        else Color.Black
                                    )
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            cursorColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            focusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                        ),
                        //Show error only if touched and password is not empty but weak
                        isError = isPasswordTouched && password.isNotEmpty() && !isPasswordStrong,
                        supportingText = if (isPasswordTouched && password.isNotEmpty() && !isPasswordStrong) {
                            {
                                Text(
                                    getPasswordStrengthMessage(password),
                                    color = MaterialTheme.colorScheme.onTertiaryContainer
                                )
                            }
                        } else null
                    )
                    // Confirm Password text field
                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = {
                            confirmPassword = it
                            if (!isConfirmPasswordTouched) isConfirmPasswordTouched = true
                        },
                        label = { Text("Confirm Password*", fontStyle = FontStyle.Italic) },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Lock,
                                contentDescription = "Password Icon",
                                tint = if (isSystemInDarkTheme()) Color.White
                                else Color.Black,
                            )
                        },
                        visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = {
                                isConfirmPasswordVisible = !isConfirmPasswordVisible
                            }) {
                                Image(
                                    painter = painterResource(id = if (isPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
                                    contentDescription = "Toggle Password Visibility",
                                    modifier = Modifier.size(24.dp),
                                    colorFilter = ColorFilter.tint(
                                        if (isSystemInDarkTheme()) Color.White
                                        else Color.Black
                                    )
                                )
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedBorderColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            cursorColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            focusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                            unfocusedLabelColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black,
                        ),
                        // Show error only if touched and passwords don't match
                        isError = isConfirmPasswordTouched && confirmPassword.isNotEmpty() && !isPasswordMatch,
                        supportingText = if (isConfirmPasswordTouched && confirmPassword.isNotEmpty() && !isPasswordMatch) {
                            {
                                Text(
                                    "Passwords do not match",
                                    color = MaterialTheme.colorScheme.error
                                )
                            }
                        } else null
                    )
                    Spacer(Modifier.height(24.dp))

                    Dialog(
                        showDialog = showDialog,
                        onDismiss = { showDialog = false },
                        title = "Error",
                        message = "User already exists"
                    )

                    // Color will change  if the form is valid
                    Button(
                        onClick = {
                            if (isFormValid) {
                                //userExists = true for now
                                if (isUserExist) {
                                    navController.popBackStack()
                                } else {
                                    showDialog = true
                                }
                                //TODO: Add a toast that "you have signed up successfully"
                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isSystemInDarkTheme()) Color.White
                            else Color.Black
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 8.dp),
                        enabled = isFormValid
                    ) {
                        Text("Sign Up", color = Color.White)
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
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

            SocialLoginButton(
                icon = painterResource(id = R.drawable.google),
                text = "Continue with Google"
            )

            Spacer(modifier = Modifier.height(12.dp))

            SocialLoginButton(
                icon = painterResource(id = R.drawable.github),
                text = "Continue with Github"
            )
            Spacer(modifier = Modifier.height(12.dp))

            Row {
                Text("Already have an account? ", color = Color.Black)
                Text(
                    "Sign in",
                    textDecoration = TextDecoration.Underline,
                    color = Color.Blue,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.clickable {
                        navController.popBackStack()
                        navController.navigate(OnboardingScreens.Sign_in.route)
                    }
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
fun Sign_up_Preview() {
    Sign_up_view(navController = rememberNavController())
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Sign_up_DarkPreview() {
    Sign_up_view(navController = rememberNavController())
}