package com.poid.baseline.light.presentation.ui.screen.master

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.poid.baseline.light.presentation.ui.theme.MediumGray
import com.poid.baseline.light.R

@Composable
fun EmptyContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            modifier = Modifier.size(126.dp),
            painter = painterResource(id = android.R.drawable.ic_delete),
            contentDescription = stringResource(id = R.string.content_empty),
            tint = MediumGray
        )
        Text(
            text = stringResource(R.string.content_empty),
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize
        )
    }
}


@Preview
@Composable
fun EmptyContentPreview() {
    EmptyContent()
}