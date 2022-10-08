package com.poid.baseline.light.presentation.navigation

import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.poid.baseline.light.presentation.SharedViewModel
import com.poid.baseline.light.presentation.base.Constants.DETAILS_ARGUMENT_KEY
import com.poid.baseline.light.presentation.base.Constants.DETAILS_SCREEN
import com.poid.baseline.light.presentation.base.Constants.MASTER_ARGUMENT_KEY
import com.poid.baseline.light.presentation.base.Constants.MASTER_SCREEN
import com.poid.baseline.light.presentation.ui.screen.details.DetailsScreen
import com.poid.baseline.light.presentation.ui.screen.master.MasterScreen

fun NavGraphBuilder.masterComposable(
    navigateToDetailsScreen: (detailsId: String) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = MASTER_SCREEN,
        arguments = listOf(navArgument(MASTER_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->

        MasterScreen(
            navigateToDetailsScreen = navigateToDetailsScreen,
            sharedViewModel = sharedViewModel
        )
    }
}

fun NavGraphBuilder.detailsComposable(
    navigateToDetailsScreen: (detailsId: String) -> Unit,
    sharedViewModel: SharedViewModel
) {
    composable(
        route = DETAILS_SCREEN,
        arguments = listOf(navArgument(DETAILS_ARGUMENT_KEY) {
            type = NavType.StringType
        })
    ) { navBackStackEntry ->
        val argument = navBackStackEntry.arguments!!.getString(DETAILS_ARGUMENT_KEY)

        LaunchedEffect(key1 = true) {

        }

        DetailsScreen(
            sharedViewModel = sharedViewModel,
            navigateToDetailsScreen = navigateToDetailsScreen
        )
    }
}