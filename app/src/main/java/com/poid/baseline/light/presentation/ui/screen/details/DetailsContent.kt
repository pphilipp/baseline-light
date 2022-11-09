package com.poid.baseline.light.presentation.ui.screen.details

import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import com.poid.baseline.light.presentation.ui.screen.master.EmptyContent
import com.poid.baseline.light.presentation.ui.theme.DETAILS_ITEM_ELEVATION
import com.poid.baseline.light.presentation.ui.theme.DetailsItemBackgroundColor
import com.poid.baseline.light.presentation.ui_model.*

@Composable
fun DetailsContent(
    paddingValues: PaddingValues,
    requestState: RequestUiState<String>
) {
    when (requestState) {
        RequestUiState.Idle -> {}
        RequestUiState.Loading -> {}
        is RequestUiState.Success -> {
            ShowDetailsContent(requestState.data)
        }
        is RequestUiState.Error -> {
            EmptyContent()
        }

    }
}

@Composable
private fun ShowDetailsContent(
    content: String,
) {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.DetailsItemBackgroundColor,
        shape = RectangleShape,
        elevation = DETAILS_ITEM_ELEVATION,

    ) {
        Text(text = content)
    }
}

@Preview
@Composable
fun DetailsItemPreview() {
    ShowDetailsContent("Item string")
}