package com.mschiretech.crm_android.customer.dashBoard

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.R
import com.mschiretech.crm_android.deviceDetails.ScreenHeight
import com.mschiretech.crm_android.deviceDetails.ScreenWidth
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(
    navController: NavController,
    //viewModel:dashBoardViewModel= viewModel()
) {
    //Colors
    val screenColor = if (isSystemInDarkTheme()) Brush.linearGradient(
        colors = listOf(Color(0xFF0D0D0D), Color(0xFF5f4B8B), Color(0xFFcbbbf6))
    ) else Brush.linearGradient(colors = listOf(Color(0xff2b2b2b), Color(0xffa593e0), Color(0xffdcd6f7)))
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val iconsColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val cardColors = if (isSystemInDarkTheme()) Color(0xFF757373) else Color.White
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    //Device Info
    val screenHeight = ScreenHeight().toString()
    val screenWidth = ScreenWidth().toString()

    val drawerWidth = if (ScreenWidth() > 600.dp) 320.dp else 250.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(drawerWidth)
            ) {
                DrawerContent(
                    navController = navController,
                    screenColor = Color.White,
                    textColor = textColor,
                    iconsColor = iconsColor
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    navController = navController,
                    title = "Dashboard",
                    textColor = textColor,
                    iconsColor = iconsColor,
                    onMenuClick = {
                        scope.launch {
                            drawerState.apply { if (isClosed) open() else close() }
                        }
                    },
                    onAccountClick = {
                        //  navController.navigate(CustomerScreens.Account.route)
                    }
                )
            }
        ) { paddingValues ->
            LazyColumn(
                Modifier
                    .fillMaxSize()
                    .background(
                        screenColor
                    )
                    .padding(paddingValues)
            ) {
                item {
                    Text(ScreenWidth().toString())
                    Text(ScreenHeight().toString())
                    Products(
                        navController = navController,
                        textColor = textColor,
                        iconsColor = iconsColor,
                        cardColors
                    )
                }
            }
        }
    }
}

@Composable
fun Products(
    navController: NavController,
    textColor: Color,
    iconsColor: Color,
    cardColors: Color
) {
    Card(
        elevation = CardDefaults.cardElevation(2.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp)
    ) {
        Column(

        )
        {
            Text(
                "Products",
                modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 16.dp),
                color = textColor,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
        LazyRow(

        ) {
            item {
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
            else -> screenWidthDp * 0.8f
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
            .padding(12.dp)
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    navController: NavController,
    title: String = "",
    textColor: Color,
    iconsColor: Color,
    onMenuClick: () -> Unit,
    onAccountClick: () -> Unit
) {
    val topBarColor = if (isSystemInDarkTheme()) Color(0xFF3F3E3E) else Color(0x20D84CEE)
    TopAppBar(
        title = {
            Text(
                title,
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif
            )
        },
        modifier = Modifier
            .clip(RoundedCornerShape(32.dp))
            .fillMaxWidth(),
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    Icons.Default.Menu,
                    contentDescription = "Menu",
                    modifier = Modifier.size(28.dp)

                )
            }
        },
        actions = {
            IconButton(onClick = onAccountClick) {
                Icon(
                    Icons.Default.Search,
                    contentDescription = "Search",
                    modifier = Modifier
                        .size(28.dp)
                        //.clickable(onClick = onMenuClick)
                )
            }
            IconButton(onClick = onAccountClick) {
                Icon(
                    Icons.Default.AccountCircle,
                    contentDescription = "Account",
                    modifier = Modifier.size(28.dp)
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = topBarColor,
            titleContentColor = textColor,
            navigationIconContentColor = iconsColor,
            actionIconContentColor = iconsColor
        ),
    )
}

@Composable
fun DrawerContent(
    navController: NavController,
    screenColor: Color,
    textColor: Color,
    iconsColor: Color
) {
    Text(
        "MSC HireTech",
        color = textColor,
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
        fontFamily = FontFamily.Serif,
        modifier = Modifier.padding(16.dp)
    )
    HorizontalDivider()
    Spacer(Modifier.height(8.dp))
    NavigationDrawerItem(
        label = { Text("Dashboard", color = textColor) },
        selected = true,
        onClick = {
            navController.navigate("dashboard")
        },
    )
    NavigationDrawerItem(
        label = { Text("Products", color = textColor) },
        selected = false,
        onClick = {
            //navController.navigate("dashboard")
        },
    )
    NavigationDrawerItem(
        label = { Text("Industries", color = textColor) },
        selected = false,
        onClick = {
            //navController.navigate("dashboard")
        },
    )
    NavigationDrawerItem(
        label = { Text("Customers", color = textColor) },
        selected = false,
        onClick = {
           // navController.navigate("dashboard")
        },
    )
    NavigationDrawerItem(
        label = { Text("Learning", color = textColor) },
        selected = false,
        onClick = {
            // navController.navigate("dashboard")
        },
    )
    NavigationDrawerItem(
        label = { Text("Support", color = textColor) },
        selected = false,
        onClick = {
            // navController.navigate("dashboard")
        },
    )
    NavigationDrawerItem(
        label = { Text("Company", color = textColor) },
        selected = false,
        onClick = {
            // navController.navigate("dashboard")
        },
    )
    NavigationDrawerItem(
        label = { Text("Settings", color = textColor) },
        selected = false,
        onClick = {
            // navController.navigate("dashboard")
        },
    )
}


@Preview(uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun DashBoardPreview() {
    DashBoard(navController = rememberNavController())
}

@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun DashBoardDarkPreview() {
    DashBoard(navController = rememberNavController())
}