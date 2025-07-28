package com.mschiretech.crm_android.customer.dashBoard.drawerItems

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun Support(
    navController: NavController,
    onHelpCenterClick: () -> Unit = {},
    onKnownIssuesClick: () -> Unit = {},
    onDeveloperDocClick: () -> Unit = {},

) {
    //Color
    val screenColor = if (isSystemInDarkTheme()) Brush.linearGradient(
        colors = listOf(Color(0xFF0D0D0D), Color(0xFF5f4B8B), Color(0xFFcbbbf6))
    ) else Brush.linearGradient(colors = listOf(Color(0xff2b2b2b), Color(0xffa593e0), Color(0xffdcd6f7)))
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val iconsColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val cardColors = if (isSystemInDarkTheme()) Color(0xFF757373) else Color.White

    Scaffold {innerPadding->
        Column(
            modifier = Modifier
                .background(screenColor)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(innerPadding)

        ) {

            Spacer(modifier = Modifier.height(60.dp))

            // Title
            Text(
                text = "Help & Documentation",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = "Need help? Log cases, find documentation, and more – all the support you need, wherever you need it.",
                fontSize = 16.sp,
                color = textColor
            )

            Spacer(modifier = Modifier.height(20.dp))

            // Visit the Help Center (link)
            Text(
                text = "Visit the Help Center",
                color =textColor,
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier
                    .clickable { onHelpCenterClick() }
            )

            Spacer(modifier = Modifier.height(36.dp))

            // Known Issues (external link icon optional)
            Text(
                text = "Known Issues",
                color = Color(0xFF18305B),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onKnownIssuesClick() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Developer Documentation (external link icon optional)
            Text(
                text = "Developer Documentation",
                color = Color(0xFF18305B),
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
                modifier = Modifier.clickable { onDeveloperDocClick() }
            )
        }
        Surface(
            modifier = Modifier.padding(innerPadding).padding(horizontal = 16.dp).clip(CircleShape),
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
    }

}
@Preview
@Composable
fun SupportView(){
    Support(rememberNavController())
}
