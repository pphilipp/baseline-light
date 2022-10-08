package com.poid.baseline.light.presentation.ui.screen.master

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.poid.baseline.light.presentation.SharedViewModel

@Composable
fun MasterScreen(
    navigateToDetailsScreen: (detailsId: String) -> Unit,
    sharedViewModel: SharedViewModel,
    scaffoldState: ScaffoldState = rememberScaffoldState()
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.fetchMasterContent()
    }

    val masterList by sharedViewModel.masterListFlow.collectAsState()

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            MasterAppBar(sharedViewModel = sharedViewModel)
        },
        content = { paddingValues ->
            MasterContent(
                paddingValues = paddingValues,
                requestState = masterList,
                navigateToDetailsScreen = navigateToDetailsScreen
            )
        }
    )
}
