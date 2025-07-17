package com.mschiretech.crm_android.core.rootNavGraph

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mschiretech.crm_android.Onboarding.navGraph.OnboardingScreens
import com.mschiretech.crm_android.Onboarding.navGraph.authGraph
import com.mschiretech.crm_android.customer.navigation.CustomerGraph

@Composable
fun RootNavGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = OnboardingScreens.AuthRoute.route) {
        authGraph(navController = navController)
        CustomerGraph(navController = navController)
    }
}
