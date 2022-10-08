package com.poid.baseline.light.presentation.navigation

import androidx.navigation.NavHostController
import com.poid.baseline.light.presentation.base.Constants.MASTER_SCREEN


class Screens(
    navHostController: NavHostController
) {

    val master: () -> Unit = {
        navHostController.navigate("master/") {
            popUpTo(MASTER_SCREEN) { inclusive = true }
        }
    }

    val details: (String) -> Unit = { detailsUrl ->
        navHostController.navigate("details/${detailsUrl}")
    }
}