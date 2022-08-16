package com.app.ml.ui.designSystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BackButton(
    modifier: Modifier,
    onBackPressed: () -> Unit
) {
    Box(
        modifier = modifier
            .testTag("back_button")
            .clickable { onBackPressed.invoke() }
            .clip(CircleShape)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(12.dp)
    ) {
        Icon(
            imageVector = Icons.Default.ArrowBack,
            contentDescription = "Back",
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Composable
@Preview
fun PreviewBackButton() {
    BackButton(
        modifier = Modifier
    ){ }
}