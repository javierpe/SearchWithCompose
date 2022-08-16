package com.app.ml.ui.designSystem

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ml.ui.theme.Error

@Composable
fun SearchErrorAlertView(
    modifier: Modifier = Modifier,
    errorMessage: String
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Close,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(50.dp)
                .testTag("search_error_icon"),
            colorFilter = ColorFilter.tint(color = Error)
        )
        Text(
            text = errorMessage,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.testTag("search_error_message")
        )
    }
}

@Composable
@Preview
fun PreviewSearchErrorAlertView() {
    SearchErrorAlertView(
        errorMessage = "Error"
    )
}