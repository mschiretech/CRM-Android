package com.mschiretech.crm_android.Onboarding.Forgot_password

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
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
import com.mschiretech.crm_android.core.internet.NetworkState
import com.mschiretech.crm_android.core.internet.observeNetworkState
import com.mschiretech.crm_android.dialogs.Dialog
import com.mschiretech.crm_android.dialogs.NoInternetDialog
import com.mschiretech.crm_android.ui.theme.accent
import com.mschiretech.crm_android.ui.theme.borderDark
import com.mschiretech.crm_android.ui.theme.borderLight
import com.mschiretech.crm_android.ui.theme.navy
import com.mschiretech.crm_android.ui.theme.peach
import com.mschiretech.crm_android.ui.theme.textDark
import com.mschiretech.crm_android.ui.theme.textLight
import com.mschiretech.crm_android.varifications.email.isValidEmail
import com.mschiretech.crm_android.varifications.otp.otp_verification
import com.mschiretech.crm_android.varifications.password.getPasswordStrengthMessage
import com.mschiretech.crm_android.varifications.password.isStrongPassword
import com.mschiretech.crm_android.varifications.userFinder.isEmailExist


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Forgot_password_view(
    navController: NavController,
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
    val iconColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    var email by remember { mutableStateOf("") }
    var showOptSection by remember { mutableStateOf(false) }
    var showResetPasswordSection by remember { mutableStateOf(false) }
    var isEmailTouched by remember { mutableStateOf(false) }

    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE


    //Internet
    val context = LocalContext.current
    val networkState by context.observeNetworkState().collectAsState(initial = NetworkState.Unavailable)
    var showNoInternetDialog by remember { mutableStateOf(true) }

    if (networkState == NetworkState.Lost) showNoInternetDialog = true
    if (networkState == NetworkState.Available) showNoInternetDialog = false


    var showDialog by remember { mutableStateOf(false) }

    val isEmailValid by remember {
        derivedStateOf { isValidEmail(email) }
    }
    val isEmailExist by remember {
        derivedStateOf { isEmailExist(email) }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Forgot Password", color = textColor) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.Transparent
                )
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .verticalScroll(scrollState)
                .padding(paddingValues)
                .padding(horizontal = 24.dp),
        ) {
            Column {
                Text(
                    text = "Reset Your Password",
                    style = MaterialTheme.typography.headlineMedium.copy(
                        fontWeight = FontWeight.Bold
                    ),
                    color = textColor
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "Enter your email address and we'll send you an OTP to reset your password",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            if (showNoInternetDialog) {
                NoInternetDialog(showDialog = showNoInternetDialog, onDismissRequest = { showNoInternetDialog = false })
            }

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
                            tint = iconColor
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    singleLine = true,
                    shape = RoundedCornerShape(24.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = borderColor,
                        unfocusedBorderColor = borderColor,
                        cursorColor = borderColor,
                        focusedLabelColor = labelColor,
                        unfocusedLabelColor = labelColor
                    ), isError = isEmailTouched && email.isNotEmpty() && !isEmailValid,
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
                Dialog(
                    showDialog = showDialog,
                    onDismiss = { showDialog = false },
                    title = "Email not found",
                    message = "Please enter a valid email address"
                )
                Button(
                    onClick = {
                        if (isEmailValid) {
                            if (isEmailExist) {
                                // Add logic to send reset password link to email
                                showOptSection = true
                            } else {
                                showDialog = true
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = (buttonBg)
                    ),
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    enabled = isEmailValid
                ) {
                    Text(
                        "Send OTP",
                        color = buttonText,
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
    val isDark = isSystemInDarkTheme()
    val borderColor = if (isDark) borderDark else borderLight
    val labelColor = borderColor
    val buttonBg = accent
    val buttonText = Color.White
    val textColor = if (isDark) textDark else textLight
    val iconColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    var showDialog by remember { mutableStateOf(false) }
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
        Text(
            "OTP sent to $email check your inbox.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.7f)
        )
        Spacer(Modifier.height(16.dp))
        Text(
            "Enter OTP",
            style = MaterialTheme.typography.headlineSmall.copy(
                fontWeight = FontWeight.Bold,
            ),
            color =textColor,
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
                            focusedBorderColor = borderColor,
                            unfocusedBorderColor = borderColor,
                            cursorColor = borderColor,
                            focusedLabelColor = labelColor,
                            unfocusedLabelColor = labelColor
                        ),
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            Dialog(
                showDialog = showDialog,
                onDismiss = { showDialog = false },
                title = "OTP matched",
                message = "Please check the otp"
            )

            Button(
                onClick = {
                    val enteredOTP = otpValues.joinToString("")
                    if (otp_verification(enteredOTP, receivedOTP)) {
                        onOTPVerified()
                    } else showDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =buttonBg
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                enabled = otpValues.all { it.isNotEmpty() }
            ) {
                Text(
                    "Verify OTP",
                    color = buttonText,
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
    val isDark = isSystemInDarkTheme()
    val borderColor = if (isDark) borderDark else borderLight
    val labelColor = borderColor
    val buttonBg = accent
    val buttonText = Color.White
    val textColor = if (isDark) textDark else textLight
    val iconColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    var password by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isPasswordTouched by remember { mutableStateOf(false) }
    var successDialog by remember { mutableStateOf(false) }
    var errorDialog by remember { mutableStateOf(false) }

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
            color =textColor
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
                        tint = iconColor
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
                                iconColor
                            )
                        )
                    }
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                singleLine = true,
                shape = RoundedCornerShape(24.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = borderColor,
                    unfocusedBorderColor = borderColor,
                    cursorColor = borderColor,
                    focusedLabelColor = labelColor,
                    unfocusedLabelColor = labelColor
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

            Dialog(
                successDialog,
                onDismiss = {
                    successDialog = false
                },
                title = "Success",
                message = "Your Password is changed successfully"
            )
            //If Password is not updated
            Dialog(
                errorDialog,
                onDismiss = {
                    errorDialog = false
                },
                title = "Error",
                message = "Some error occurred"
            )
            Button(
                onClick = {
                    if (isPasswordStrong) {
                        // Add logic to update password in backend
                        successDialog = true

                    } else errorDialog = true
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor =buttonBg
                ),
                modifier = Modifier
                    .padding(horizontal = 8.dp),
                enabled = isPasswordStrong
            ) {
                Text(
                    "Reset Password",
                    color =buttonText,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )
            }
        }
    }
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun Forgot_password_ScreenPreview() {
    Forgot_password_view(navController = rememberNavController())
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun Forgot_password_ScreenDarkPreview() {
    Forgot_password_view(navController = rememberNavController())
}