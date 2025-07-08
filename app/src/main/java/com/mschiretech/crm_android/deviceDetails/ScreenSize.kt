package com.mschiretech.crm_android.deviceDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScreenSize(): Dp {
    val configuration = LocalConfiguration.current
    val _screenWidth = configuration.screenWidthDp.dp
    return _screenWidth
}