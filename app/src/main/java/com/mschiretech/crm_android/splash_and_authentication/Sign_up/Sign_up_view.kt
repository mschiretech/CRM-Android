package com.mschiretech.crm_android.splash_and_authentication.Sign_up

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
                colors = CardDefaults.cardColors(Color(0xFF9BAEDA)),
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
                                tint = Color.Black
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black
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
                                tint = Color.Black
                            )
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                        singleLine = true,
                        shape = RoundedCornerShape(24.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Gray,
                            unfocusedBorderColor = Color.Black,
                            cursorColor = Color.Black,
                            focusedLabelColor = Color.Black,
                            unfocusedLabelColor = Color.Black
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
                                tint = Color.Black
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
                        //Show error only if touched and password is not empty but weak
                        isError = isPasswordTouched && password.isNotEmpty() && !isPasswordStrong,
                        supportingText = if (isPasswordTouched && password.isNotEmpty() && !isPasswordStrong) {
                            {
                                Text(
                                    "Password must be 8+ chars with uppercase, lowercase, number & special character",
                                    color = MaterialTheme.colorScheme.error
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
                                tint = Color.Black
                            )
                        },
                        visualTransformation = if (isConfirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            IconButton(onClick = {
                                isConfirmPasswordVisible = !isConfirmPasswordVisible
                            }) {
                                Image(
                                    painter = painterResource(id = if (isConfirmPasswordVisible) R.drawable.visibility else R.drawable.visibility_off),
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

                    // Color will change  if the form is valid
                    Button(
                        onClick = {
//                            if (isFormValid) {
//                                // Navigate to next screen
//                                // navController.navigate(OnbordingScreens.NextScreen.route)
//                            }
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Black
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
                        navController.navigate(OnbordingScreens.Sign_in.route)
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

//validation functions
fun isValidEmail(email: String): Boolean {
    return email.isNotEmpty() && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}

fun isStrongPassword(password: String): Boolean {
    // Check minimum length
    if (password.length < 8) return false

    // Check for at least one lowercase letter
    val hasLowercase = password.any { it.isLowerCase() }

    // Check for at least one uppercase letter
    val hasUppercase = password.any { it.isUpperCase() }

    // Check for at least one digit
    val hasDigit = password.any { it.isDigit() }

    // Check for at least one special character
    val specialChars = "!@#$%^&*()_+-=[]{}|;:,.<>?"
    val hasSpecialChar = password.any { it in specialChars }

    return hasLowercase && hasUppercase && hasDigit && hasSpecialChar
}


fun getPasswordStrengthMessage(password: String): String {
    if (password.isEmpty()) return ""

    val issues = mutableListOf<String>()

    if (password.length < 8) issues.add("8+ characters")
    if (!password.any { it.isLowerCase() }) issues.add("lowercase letter")
    if (!password.any { it.isUpperCase() }) issues.add("uppercase letter")
    if (!password.any { it.isDigit() }) issues.add("number")
    if (!password.any { it in "!@#$%^&*()_+-=[]{}|;:,.<>?" }) issues.add("special character")

    return if (issues.isEmpty()) {
        "Strong password!"
    } else {
        "Missing: ${issues.joinToString(", ")}"
    }
}

@Composable
fun PasswordStrengthIndicator(password: String) {
    val strength = when {
        password.isEmpty() -> 0
        password.length < 8 -> 1
        !password.any { it.isLowerCase() } || !password.any { it.isUpperCase() } -> 2
        !password.any { it.isDigit() } -> 3
        !password.any { it in "!@#$%^&*()_+-=[]{}|;:,.<>?" } -> 4
        else -> 5
    }

    val color = when (strength) {
        0, 1 -> Color.Red
        2, 3 -> Color.Red
        4 -> Color.Yellow
        5 -> Color.Green
        else -> Color.Gray
    }

    val strengthText = when (strength) {
        0 -> ""
        1 -> "Very Weak"
        2 -> "Weak"
        3 -> "Fair"
        4 -> "Good"
        5 -> "Strong"
        else -> ""
    }

    if (password.isNotEmpty()) {
        Text(
            text = strengthText,
            color = color,
            style = MaterialTheme.typography.bodySmall
        )
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