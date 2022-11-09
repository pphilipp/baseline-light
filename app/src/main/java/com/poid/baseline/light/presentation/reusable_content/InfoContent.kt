package com.poid.baseline.light.presentation.reusable_content

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.poid.baseline.light.presentation.ui.theme.MediumGray
import com.poid.baseline.light.presentation.ui.theme.TopAppBarContentColor

@Composable
fun InfoContent(
    modifier: Modifier,
    message: String,
    iconRes: Int
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = modifier.size(52.dp),
            painter = painterResource(id = iconRes),
            contentDescription = message,
        )
        Text(
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = MediumGray,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.h6.fontSize,
            text = message,
            style = TextStyle(
                fontSize = 24.sp,
                shadow = Shadow(
                    color = MaterialTheme.colors.TopAppBarContentColor,
                    offset = Offset(5.0f, 10.0f),
                    blurRadius = 3f
                )
            )
        )
    }
}
