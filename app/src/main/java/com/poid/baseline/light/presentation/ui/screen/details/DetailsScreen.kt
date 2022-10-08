package com.poid.baseline.light.presentation.ui.screen.details

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.poid.baseline.light.presentation.SharedViewModel

@Composable
fun DetailsScreen(
    sharedViewModel: SharedViewModel,
    navigateToDetailsScreen: (detailsId: String) -> Unit,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {
    val allDetails by sharedViewModel.detailsFlow.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            DetailsAppBar(sharedViewModel = sharedViewModel)
        },
        content = { paddingValues ->
            DetailsContent(
                paddingValues = paddingValues,
                requestState =  allDetails
            )
        }
    )

}