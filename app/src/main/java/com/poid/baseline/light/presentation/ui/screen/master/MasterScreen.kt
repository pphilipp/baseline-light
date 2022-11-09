package com.poid.baseline.light.presentation.ui.screen.master

import android.annotation.SuppressLint
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.poid.baseline.light.R
import com.poid.baseline.light.domain.model.ConnectionStateModel
import com.poid.baseline.light.presentation.SharedViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun MasterScreen(
    navigateToDetailsScreen: (detailsId: String) -> Unit,
    sharedViewModel: SharedViewModel,
) {
    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    val masterList by sharedViewModel.masterListFlow.collectAsStateWithLifecycle()
    val connectionState by sharedViewModel.connectionState.collectAsStateWithLifecycle()

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

            SnackBarNetworkState(
                connectionState,
                coroutineScope,
                scaffoldState
            ) {
                sharedViewModel.markConnectionStateAsShown()
            }
        }
    )
}

@Composable
private fun SnackBarNetworkState(
    connectionModel: Pair<ConnectionStateModel, Boolean>,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    doOnShown: (Boolean) -> Unit,
) {
    val isNotYetShown = !connectionModel.second

    if (isNotYetShown) {
        when (connectionModel.first) {
            ConnectionStateModel.Available -> ShowSnackBar(
                message = stringResource(R.string.text_online),
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState,
                doOnShown = doOnShown
            )
            ConnectionStateModel.Unavailable -> ShowSnackBar(
                message = stringResource(R.string.text_offline),
                coroutineScope = coroutineScope,
                scaffoldState = scaffoldState,
                doOnShown = doOnShown
            )
            ConnectionStateModel.Idle -> {}
        }
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Composable
private fun ShowSnackBar(
    message: String,
    coroutineScope: CoroutineScope,
    scaffoldState: ScaffoldState,
    doOnShown: (Boolean) -> Unit
) {
    coroutineScope.launch {
        val snackBarResult = scaffoldState.snackbarHostState.showSnackbar(
            message = message,
            duration = SnackbarDuration.Short
        )

        if (snackBarResult == SnackbarResult.Dismissed) {
            doOnShown.invoke(true)
        }
    }
}