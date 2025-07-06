package com.mschiretech.crm_android.Onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.R
import com.mschiretech.crm_android.navGraph.OnboardingScreens
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavController
    ) {
    var isVisible by remember { mutableStateOf(false) }

    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.5f,
        animationSpec = tween(2000, easing = FastOutSlowInEasing)
    )

    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f,
        animationSpec = tween(1000, easing = FastOutSlowInEasing)
    )

    LaunchedEffect(Unit) {
        isVisible = true
        delay(1000)
        navController.popBackStack()
        navController.navigate(OnboardingScreens.Sign_in.route)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isSystemInDarkTheme()) Color(0xFF101010)  else  Color(0xFF3F58E1)),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(visible = isVisible) {
            Image(
                painter = painterResource(id = R.drawable.app),
                contentDescription = "App Logo",
                modifier = Modifier
                    .size(140.dp)
                    .graphicsLayer {
                        scaleX = scale
                        scaleY = scale
                        this.alpha = alpha
                    }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen(navController = rememberNavController())
}
