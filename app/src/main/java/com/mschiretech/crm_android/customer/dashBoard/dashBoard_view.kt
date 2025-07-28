package com.mschiretech.crm_android.customer.dashBoard

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Blinds
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.SupportAgent
import androidx.compose.material.icons.filled.TempleHindu
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.R
import com.mschiretech.crm_android.core.deviceDetails.ScreenHeight
import com.mschiretech.crm_android.core.deviceDetails.ScreenWidth
import com.mschiretech.crm_android.customer.navigation.CustomerScreens
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

    var isDashboardEmpty by remember { mutableStateOf(true) }
    var isSearchClicked by remember { mutableStateOf(false) }

    val drawerState = rememberDrawerState(DrawerValue.Open)
    val scope = rememberCoroutineScope()

    //Device Info
    val screenHeight = ScreenHeight()
    val screenWidth = ScreenWidth()

    val drawerWidth = if (screenWidth > 600.dp) 320.dp else 250.dp

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.width(drawerWidth)
            ) {
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
                    title = "Dashboard",
                    textColor = textColor,
                    iconsColor = iconsColor,
                    onMenuClick = {
                        scope.launch {
                            drawerState.apply { if (isClosed) open() else close() }
                        }
                    },
                    onAccountClick = {
                        navController.navigate(CustomerScreens.Account.route)
                    },
                    onSearchClick = {
                        isSearchClicked = true
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
                    // MySearchScreen()

                }
            }
            if (isDashboardEmpty) {
                //If the dashboard is empty, show an empty dashboard
                Box(
                    modifier = Modifier
                        .height(screenHeight)
                        .fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Column {
                        Image(
                            painter = painterResource(R.drawable.empty),
                            contentDescription = "App Logo",
                            modifier = Modifier.size(200.dp),
                            alpha = 0.6f
                        )
                        Text(
                            "Empty dashboard",
                            fontWeight = MaterialTheme.typography.headlineMedium.fontWeight,
                            fontSize = 32.sp,
                            fontFamily = FontFamily.SansSerif
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String = "",
    textColor: Color,
    iconsColor: Color,
    onMenuClick: () -> Unit,
    onAccountClick: () -> Unit,
    onSearchClick: () -> Unit
) {
    val topBarColor = if (isSystemInDarkTheme()) Color(0xE4352B36) else Color(0x20D84CEE)
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
                        .clickable(onClick = onSearchClick)
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
    screenColor: Brush,
    textColor: Color,
    iconsColor: Color,
    onCloseDrawer: () -> Unit = {} // Optional callback to close drawer after navigation
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    data class DrawerItem(val label: String, val route: String?, val icon: ImageVector?)

    val drawerItems = listOf(
        DrawerItem("Dashboard", CustomerScreens.Dashboard.route, Icons.Default.Home),
        DrawerItem("Products", CustomerScreens.Product.route, Icons.Default.ShoppingCart),
        DrawerItem("Support", CustomerScreens.Support.route, Icons.Default.SupportAgent),
        DrawerItem(label ="Industries", CustomerScreens.Industries.route, Icons.Default.Blinds)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(screenColor),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(
                "MSC HireTech",
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = FontFamily.Serif,
                modifier = Modifier.padding(16.dp)
            )

            HorizontalDivider(color = textColor.copy(alpha = 0.3f))
            Spacer(Modifier.height(8.dp))

            drawerItems.forEach { item ->
                NavigationDrawerItem(
                    label = {
                        Text(
                            item.label,
                            color = textColor,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            fontFamily = FontFamily.SansSerif
                        )
                    },
                    icon = item.icon?.let { icon ->
                        { Icon(icon, contentDescription = item.label, tint = iconsColor) }
                    },
                    selected = false,
                    onClick = {
                        item.route?.let {
                            if (currentRoute != it) {
                                navController.navigate(it) {
                                    popUpTo(navController.graph.startDestinationId) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                            onCloseDrawer()
                        }
                    }
                )
            }
        }
        Box(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Phone, contentDescription = "Contact")
                    Text(
                        "Contact",
                        color = textColor,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        fontFamily = FontFamily.SansSerif
                    )
                }
                Text(
                    "xxxx xxxx xx",
                    color = textColor,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = FontFamily.SansSerif
                )
                Spacer(modifier = Modifier.height(16.dp))
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MySearchScreen() {
    var query by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    val searchHistory = remember { mutableStateListOf<String>() }
    val searchResults = remember(query, active) {
        if (query.isNotBlank() && active) {
            // Simulate filtering based on query
            listOf("Result for $query 1", "Result for $query 2", "Another $query result")
                .filter { it.contains(query, ignoreCase = true) }
        } else {
            emptyList()
        }
    }

    Box(Modifier.fillMaxWidth()) { // Often SearchBar is placed within a Box or Column
        SearchBar(
            modifier = Modifier
                .align(Alignment.TopCenter) // Example alignment
                .padding(top = 8.dp),
            query = query,
            onQueryChange = { newQuery -> query = newQuery },
            onSearch = { searchQuery ->
                println("User searched for: $searchQuery")
                if (searchQuery.isNotBlank() && !searchHistory.contains(searchQuery)) {
                    searchHistory.add(0, searchQuery) // Add to top of history
                }
                active = false // Typically close the search view after search
            },
            active = active,
            onActiveChange = { isActive -> active = isActive },
            placeholder = { Text("Search...") },
            leadingIcon = {
                Icon(Icons.Default.Search, contentDescription = "Search Icon")
            },
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = { query = "" }) {
                        Icon(Icons.Default.Clear, contentDescription = "Clear Query")
                    }
                }
            }
        ) { // This is the content lambda you asked about
            // Display search history if query is empty and SearchBar is active
            if (query.isBlank() && searchHistory.isNotEmpty()) {
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(searchHistory) { historyItem ->
                        Text(
                            text = historyItem,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    query = historyItem
                                    // Optionally trigger search or just populate query
                                    // onSearch(historyItem)
                                }
                                .padding(16.dp)
                        )
                    }
                }
            } else if (searchResults.isNotEmpty()) {
                // Display search results if available
                LazyColumn(modifier = Modifier.fillMaxWidth()) {
                    items(searchResults) { result ->
                        Text(
                            text = result,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    // Handle click on a search result
                                    println("Clicked on result: $result")
                                    query = result // Populate search bar with result
                                    active = false // Close search view
                                }
                                .padding(16.dp)
                        )
                    }
                }
            } else if (query.isNotBlank() && searchResults.isEmpty() && active) {
                // Display "No results" message
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No results found for \"$query\"")
                }
            }
        }
    }
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