package com.poid.baseline.light.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.poid.baseline.light.presentation.SharedViewModel
import com.poid.baseline.light.presentation.base.Constants.MASTER_SCREEN

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel
) {
    val screen = remember(navController) {
        Screens(navHostController = navController)
    }

    NavHost(
        navController = navController,
        startDestination = MASTER_SCREEN
    ) {
        masterComposable(
            navigateToDetailsScreen = screen.details,
            sharedViewModel = sharedViewModel
        )

        detailsComposable(
            navigateToDetailsScreen = screen.details,
            sharedViewModel = sharedViewModel
        )
    }
}