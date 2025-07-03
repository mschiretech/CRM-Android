package com.mschiretech.crm_android.splash_and_authentication.Forgot_password

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.R
import com.mschiretech.crm_android.varifications.email.isValidEmail
import com.mschiretech.crm_android.varifications.otp.otp_verification
import com.mschiretech.crm_android.varifications.password.getPasswordStrengthMessage
import com.mschiretech.crm_android.varifications.password.isStrongPassword

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
        ) {
            var email by remember { mutableStateOf("") }
            var showOptSection by remember { mutableStateOf(false) }
            var showResetPasswordSection by remember { mutableStateOf(false) }
            var isEmailTouched by remember { mutableStateOf(false) }

            val isEmailValid by remember {
                derivedStateOf { isValidEmail(email) }
            }

            Text(
                "Forgot Password",
                modifier = Modifier.padding(vertical = 16.dp),
                color = Color.Black,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontStyle = androidx.compose.ui.text.font.FontStyle.Italic,
                    fontWeight = FontWeight.Bold
                )
            )
            Text(
                "Please enter your email address to receive OTP",
                modifier = Modifier,
                color = Color.Black,
                style = MaterialTheme.typography.bodyMedium
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Email text field
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
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
                Spacer(Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (isEmailValid) {
                            // Add logic to send reset password link to email
                            showOptSection = true
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Black
                    ),
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    enabled = isEmailValid
                ) {
                    Text(
                        "Send OTP",
                        color = Color.White,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
            Spacer(Modifier.height(16.dp))

            if (showOptSection) {
                IndividualOTPTextFields(
                    email = email,
                    onOTPVerified = {
                        showResetPasswordSection = true
                    }
                )
            }
            Spacer(Modifier.height(16.dp))
            if (showResetPasswordSection) {
                ResetPasswordField(
                    onPasswordReset = {
                        // Navigate back to login and show success message
                        navController.popBackStack()
                    }
                )
            }
        }
    }
}

@Composable
fun IndividualOTPTextFields(
    email: String,
    onOTPVerified: () -> Unit
) {
    val otpLength = 6
    val otpValues = remember {
        mutableStateListOf<String>().apply {
            repeat(otpLength) { add("") }
        }
    }
    val focusRequesters = remember {
        List(otpLength) { FocusRequester() }
    }
    var receivedOTP by remember { mutableStateOf("123456") }

    Column(
        modifier = Modifier.fillMaxWidth(),

    ) {
        Text("OTP sent to $email check your inbox.")
        Spacer(Modifier.height(16.dp))
        Text(
            "Enter OTP",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                repeat(otpLength) { index ->
                    OutlinedTextField(
                        value = otpValues[index],
                        onValueChange = { newValue ->
                            if (newValue.length <= 1 && newValue.all { it.isDigit() }) {
                                otpValues[index] = newValue

                                // Auto-focus next field
                                if (newValue.isNotEmpty() && index < otpLength - 1) {
                                    focusRequesters[index + 1].requestFocus()
                                }
                            }
                        },
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        singleLine = true,
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        ),
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .size(50.dp)
                            .focusRequester(focusRequesters[index]),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color.Black,
                            unfocusedBorderColor = Color.Black
                        )
                    )
                }
            }
            Spacer(Modifier.height(16.dp))

            Button(
                onClick = {
                    val enteredOTP = otpValues.joinToString("")
                    if (otp_verification(enteredOTP, receivedOTP)) {
                        onOTPVerified()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                enabled = otpValues.all { it.isNotEmpty() }
            ) {
                Text(
                    "Verify OTP",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}

@Composable
fun ResetPasswordField(
    onPasswordReset: () -> Unit
) {
    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordTouched by remember { mutableStateOf(false) }

    val isPasswordStrong by remember {
        derivedStateOf { isStrongPassword(password) }
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text(
            "Reset Password",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold
            ),
            color = Color.Black
        )
        Spacer(Modifier.height(16.dp))
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Password Text Field
            OutlinedTextField(
                value = password,
                onValueChange = {
                    password = it
                    if (!isPasswordTouched) isPasswordTouched = true
                },
                label = { Text("New Password*", fontStyle = FontStyle.Italic) },
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
                isError = isPasswordTouched && password.isNotEmpty() && !isPasswordStrong,
                supportingText = if (isPasswordTouched && password.isNotEmpty() && !isPasswordStrong) {
                    {
                        Text(
                            getPasswordStrengthMessage(password),
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                } else null,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(24.dp))

            Button(
                onClick = {
                    if (isPasswordStrong) {
                        // Add logic to update password in backend
                        onPasswordReset()
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                enabled = isPasswordStrong
            ) {
                Text(
                    "Reset Password",
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
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