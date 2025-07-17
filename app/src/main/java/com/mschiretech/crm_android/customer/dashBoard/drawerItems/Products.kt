package com.mschiretech.crm_android.customer.dashBoard.drawerItems

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.R
import com.mschiretech.crm_android.deviceDetails.ScreenHeight
import com.mschiretech.crm_android.deviceDetails.ScreenWidth

@Composable
fun Product(
    navController: NavController
){
    //Color
    val screenColor = if (isSystemInDarkTheme()) Brush.linearGradient(
        colors = listOf(Color(0xFF0D0D0D), Color(0xFF5f4B8B), Color(0xFFcbbbf6))
    ) else Brush.linearGradient(colors = listOf(Color(0xff2b2b2b), Color(0xffa593e0), Color(0xffdcd6f7)))
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val iconsColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val cardColors = if (isSystemInDarkTheme()) Color(0xFF757373) else Color.White

    Scaffold {innerPadding->

        LazyColumn(
            modifier = Modifier.background(screenColor).padding(innerPadding)
        ) {
            item {
                    Spacer(modifier = Modifier.height(60.dp))
                ProductCard(
                    R.drawable.small_bussiness,
                    title = "Small Business",
                    about = "Sales,Service and email outreach tools in a single app",
                    onCardClick = {
                        //navController.navigate("smallBusiness")
                    },
                    textColor = textColor,
                    cardColors = cardColors
                )
                ProductCard(
                    R.drawable.sales,
                    title = "Sales",
                    about = "___ your sales with us",
                    onCardClick = {
                        //navController.navigate("sales")
                    },
                    textColor = textColor,
                    cardColors = cardColors
                )
                ProductCard(
                    R.drawable.call_center,
                    title = "Service",
                    about = "___ your service with us",
                    onCardClick = {
                        //navController.navigate("service")
                    },
                    textColor = textColor,
                    cardColors = cardColors
                )
                ProductCard(
                    R.drawable.crm,
                    title = "CRM",
                    about = "Build your CRM with us",
                    onCardClick = {
                        //navController.navigate("email")
                    },
                    textColor = textColor,
                    cardColors = cardColors
                )
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
fun ProductCard(
    icon: Int,
    title: String,
    about: String,
    onCardClick: () -> Unit,
    textColor: Color = MaterialTheme.colorScheme.onSurface,
    cardColors: Color = MaterialTheme.colorScheme.surface,
    modifier: Modifier = Modifier
) {
    val screenWidthDp = ScreenWidth()
    val screenHeightDp = ScreenHeight()


    // More logical card width calculation
    val cardWidth = remember(screenWidthDp) {
        when {
            screenWidthDp >= 840.dp -> screenWidthDp * 0.25f   // Laptop/Desktop
            screenWidthDp >= 600.dp -> screenWidthDp * 0.6f   // Tablet
            else -> screenWidthDp * 1f
        }
    }

    val cardHeight = remember(screenHeightDp) {
        when {
            screenHeightDp >= 1000 .dp-> screenHeightDp * 0.36f  // 💻 Large screens
            screenHeightDp >= 800 .dp-> screenHeightDp * 0.28f   // 💊 Medium screens
            screenHeightDp >= 600 .dp-> screenHeightDp * 0.45f  // 📱 Smaller tablets
            else -> screenHeightDp * 0.6f                    // 📱 Very small phones
        }
    }

    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = cardColors),
        modifier = modifier
            .padding(horizontal = 16.dp)
            .width(cardWidth)
            .height(cardHeight)
            .wrapContentHeight()
            .clickable(
                onClick = onCardClick,
                role = Role.Button
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(id = icon),
                contentDescription = "$title icon",
                modifier = Modifier.size(54.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = title,
                color = textColor,
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.SemiBold
                ),
                textAlign = TextAlign.Center,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = about,
                color = textColor.copy(alpha = 0.7f),
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(8.dp))

            FilledTonalButton(
                onClick = onCardClick,
                modifier = Modifier
            ) {
                Text("Explore")
            }
        }
    }
}
@Preview
@Composable
fun ProductPreview(){
    Product(rememberNavController())
}
