package com.poid.baseline.light.presentation.ui.screen.master

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.poid.baseline.light.R
import com.poid.baseline.light.presentation.SharedViewModel
import com.poid.baseline.light.presentation.ui.theme.TopAppBarBackgroundColor
import com.poid.baseline.light.presentation.ui.theme.TopAppBarContentColor

@Composable
fun MasterAppBar(
    sharedViewModel: SharedViewModel
) {
    val title: String by sharedViewModel.titleMaster
    DefaultAppBar(title)
}

@Composable
fun DefaultAppBar(title: String) {
    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.TopAppBarContentColor
            )
        },
        backgroundColor = MaterialTheme.colors.TopAppBarBackgroundColor,
    )
}

@Preview
@Composable
fun DefaultAppBarPreview() {
    DefaultAppBar("Title")
}
