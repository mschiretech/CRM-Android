package com.mschiretech.crm_android.customer.dashBoard

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.deviceDetails.ScreenSize
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashBoard(
    navController: NavController,
    //viewModel:dashBoardViewModel= viewModel()
) {
    val screenColor = if (isSystemInDarkTheme()) Color.Black else Color.White
    val textColor = if (isSystemInDarkTheme()) Color.White else Color.Black
    val iconsColor = if (isSystemInDarkTheme()) Color.White else Color.Black

    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val drawerWidth = if (ScreenSize() > 600.dp) 320.dp else 250.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet (
                modifier = Modifier.width(drawerWidth)
            ){
                DrawerContent(
                    navController = navController,
                    screenColor = screenColor,
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
                    Screen(
                        navController = navController,
                        screenColor = screenColor,
                        textColor = textColor,
                        iconsColor = iconsColor,
                    )
                }
            }
        }
    }
}

@Composable
fun Screen(
    navController: NavController,
    screenColor: Color,
    textColor: Color,
    iconsColor: Color
){

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
                    Icons.Default.Notifications,
                    contentDescription = "Notifications",
                    modifier = Modifier
                        .size(28.dp)
                        .clickable(onClick = onMenuClick)
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
    NavigationDrawerItem(
        label = { Text("Dashboard", color = textColor) },
        selected = true,
        onClick = {
            navController.navigate("dashboard")
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