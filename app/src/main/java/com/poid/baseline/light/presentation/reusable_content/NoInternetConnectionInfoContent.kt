package com.poid.baseline.light.presentation.reusable_content

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun NoInternetContent(
    modifier: Modifier,
    exception: String,
) {
    InfoContent(
        modifier = modifier,
        message = exception,
        iconRes = android.R.drawable.ic_lock_power_off
    )
}

@Preview
@Composable
fun NoInternetContentPreview() {
    NoInternetContent(Modifier, "NoConnectivityException")
}