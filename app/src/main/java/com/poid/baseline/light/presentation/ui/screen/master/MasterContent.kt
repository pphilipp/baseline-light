package com.poid.baseline.light.presentation.ui.screen.master

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poid.baseline.light.presentation.ui.theme.DetailsItemBackgroundColor
import com.poid.baseline.light.presentation.ui.theme.DetailsItemTextColor
import com.poid.baseline.light.presentation.ui_model.RequestState
import com.poid.baseline.light.presentation.ui_model.MasterListItemUiModel

@Composable
fun MasterContent(
    paddingValues: PaddingValues,
    requestState: RequestState<List<MasterListItemUiModel>>,
    navigateToDetailsScreen: (detailsId: String) -> Unit
) {
    when (requestState) {
        RequestState.Idle -> {}
        RequestState.Loading -> {}
        is RequestState.Success -> {
            if (requestState.data.isEmpty()) {
                EmptyContent()
            } else {
                ShowMasterContent(requestState.data, navigateToDetailsScreen)
            }
        }
        is RequestState.Error -> {
            EmptyContent()
        }
    }
}

@Composable
private fun ShowMasterContent(
    items: List<MasterListItemUiModel>,
    navigateToDetailsScreen: (detailsId: String) -> Unit
) {
    LazyColumn {
        items(
            items = items,
            key = { item ->
                item.someData
            }
        ) { task ->
            MasterItem(
                detailsUiModel = task,
                navigateToDetailsScreen = navigateToDetailsScreen
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MasterItem(
    detailsUiModel: MasterListItemUiModel,
    navigateToDetailsScreen: (detailsId: String) -> Unit
) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.DetailsItemBackgroundColor,
        elevation = 4.dp,
        onClick = {
            navigateToDetailsScreen(detailsUiModel.someData)
        }
    ) {

        ListItem(text = {
            Text(
                text = detailsUiModel.someData,
                color = MaterialTheme.colors.DetailsItemTextColor,
                style = MaterialTheme.typography.h5,
                fontWeight = FontWeight.Normal,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        },
            icon = {
                Icon(
                    Icons.Outlined.List,
                    contentDescription = null,
                    modifier = Modifier.size(40.dp)
                )
            })
    }
}

@Preview
@Composable
fun MasterItemPreview() {
    MasterItem(MasterListItemUiModel("Hi this is item"), {})
}