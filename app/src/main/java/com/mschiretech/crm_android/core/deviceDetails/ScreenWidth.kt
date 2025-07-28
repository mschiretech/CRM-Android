package com.mschiretech.crm_android.core.deviceDetails

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ScreenWidth(): Dp {
    val configurationW = LocalConfiguration.current
    val _screenWidth = configurationW.screenWidthDp.dp
    return _screenWidth
}
@Composable
fun ScreenHeight(): Dp {
    val configurationH = LocalConfiguration.current
    val _screenHeight = configurationH.screenHeightDp.dp
    return _screenHeight
}