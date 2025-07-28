package com.mschiretech.crm_android.customer.dashBoard.drawerItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.mschiretech.crm_android.R
data class IndustriesList(val name: String, val icon: Int, val route: String)

@Composable
fun Industries(
    navController: NavController,
    onCardClick: () -> Unit = {}
) {
    //Color
    val screenColor = if (isSystemInDarkTheme()) Brush.linearGradient(
        colors = listOf(Color(0xFF0D0D0D), Color(0xFF5f4B8B), Color(0xFFcbbbf6))
    ) else Brush.linearGradient(colors = listOf(Color(0xff2b2b2b), Color(0xffa593e0), Color(0xffdcd6f7)))
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val iconsColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val cardColors = if (isSystemInDarkTheme()) Color(0xFF757373) else Color.White

    val industriesList = listOf(
        IndustriesList("Education", R.drawable.help, "agriculture_route"),
        IndustriesList("Automation", R.drawable.help, "construction_route"),
        IndustriesList("Communication", R.drawable.help, "construction_route"),
        IndustriesList("Consumer Goods", R.drawable.help, "construction_route"),
        IndustriesList("Energy & Utilities", R.drawable.help, "construction_route"),
        IndustriesList("Financial Services", R.drawable.help, "construction_route"),
        IndustriesList("Manufacturing", R.drawable.help, "construction_route"),
        IndustriesList("Media & Entertainment", R.drawable.help, "construction_route"),
        IndustriesList("Public Sector", R.drawable.help, "construction_route"),



        )

    Scaffold { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .background(screenColor)
                .fillMaxSize()
                .padding(horizontal = 24.dp)
                .padding(innerPadding)
                .padding(top = 60.dp)
        ) {
            items(industriesList) { item ->
                IndustryCard(navController, item.name, item.icon, item.route, cardColors,textColor)
            }
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

@Composable
fun IndustryCard(
    navController: NavController,
    name: String,
    icon: Int,
    route: String,
    cardColors: Color,
    textColors: Color
) {
    Card(
        modifier = Modifier.padding(vertical = 8.dp).fillMaxWidth()
            .clickable { navController.navigate(route) },
        colors = CardDefaults.cardColors(containerColor = cardColors),
        elevation = CardDefaults.cardElevation(8.dp),

    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = "Industries icon",
                modifier = Modifier
                    .clip(CircleShape)
                    .size(80.dp)
                    .padding(start = 16.dp, end = 16.dp)
            )
            Text(
                name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}
