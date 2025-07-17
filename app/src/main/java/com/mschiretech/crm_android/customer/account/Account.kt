package com.mschiretech.crm_android.customer.account

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.Onboarding.navGraph.OnboardingScreens
import com.mschiretech.crm_android.R

@Composable
fun Account(
    navController: NavController,
   // viewModel: ViewModel = viewModel()
) {
    //Color
    val screenColor = if (isSystemInDarkTheme()) Brush.linearGradient(
        colors = listOf(Color(0xFF0D0D0D), Color(0xFF5f4B8B), Color(0xFFcbbbf6))
    ) else Brush.linearGradient(
        colors = listOf(
            Color(0xff2b2b2b),
            Color(0xffa593e0),
            Color(0xffdcd6f7)
        )
    )
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val iconsColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val cardColors = if (isSystemInDarkTheme()) Color(0xFF757373) else Color.White

    Scaffold(
        modifier = Modifier.background(screenColor)
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .background(screenColor)
                .padding(innerPadding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Surface(
                modifier = Modifier.clip(CircleShape),
                color = Color(0x59FFFFFF)
            ) {
                IconButton(
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        Icons.Default.KeyboardArrowLeft,
                        contentDescription = "",
                        modifier = Modifier.size(32.dp),
                    )
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.user),
                    contentDescription = "User Image",
                    modifier = Modifier.size(180.dp),
                    contentScale = ContentScale.FillWidth
                )
            }
            Spacer(Modifier.height(16.dp))
            Options(
                title = "Details",
                icon = R.drawable.edit,
                onOptionClick = {
                    //navController.navigate("profile")
                },
                textColor = textColor,
                iconsColor = iconsColor,
                cardColors
            )
            Spacer(Modifier.height(8.dp))
            Options(
                title = "Projects",
                icon = R.drawable.crm,
                onOptionClick = {
                    //navController.navigate("profile")
                },
                textColor = textColor,
                iconsColor = iconsColor,
                cardColors
            )
            Spacer(Modifier.height(8.dp))
            Options(
                title = "Help",
                icon = R.drawable.help,
                onOptionClick = {
                    //navController.navigate("profile")
                },
                textColor = textColor,
                iconsColor = iconsColor,
                cardColors
            )
            Spacer(Modifier.height(8.dp))
            Options(
                title = "Logout",
                icon = R.drawable.logout,
                onOptionClick = {
                    navController.navigate(OnboardingScreens.Sign_in.route)
                },
                textColor = textColor,
                iconsColor = iconsColor,
                cardColors
            )
        }
    }
}

@Composable
fun Options(
    title: String = "",
    icon: Int,
    onOptionClick: () -> Unit,
    textColor: Color,
    iconsColor: Color,
    cardColors: Color = Color.White,

    ) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Gray)
            .fillMaxWidth()
            .padding(8.dp)
            .clickable(
                onClick = {
                    onOptionClick()
                }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "$title icon",
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp),
                tint = iconsColor
            )
            Text(
                title,
                color = textColor,
                modifier = Modifier,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                fontSize = 16.sp
            )
        }
        IconButton(
            onClick = onOptionClick,
            modifier = Modifier.size(40.dp)
        ){
            Icon(
                Icons.Default.KeyboardArrowRight,
                contentDescription = "$title icon",
                modifier = Modifier
                    .size(40.dp)
                    .padding(end = 16.dp),
                tint = iconsColor
            )
        }
    }
}

@Preview
@Composable
fun AccountPreview() {
    Account(rememberNavController())
}