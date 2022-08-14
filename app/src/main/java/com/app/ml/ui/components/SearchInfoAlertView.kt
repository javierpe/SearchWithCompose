package com.app.ml.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ml.R

@Composable
fun SearchInfoAlertView(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            imageVector = Icons.Default.Search,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(50.dp)
                .testTag("search_info_alert_icon"),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.secondary)
        )
        Text(
            text = stringResource(R.string.search_info_alert),
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.testTag("search_info_alert_text")
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewSearchInfoAlertView() {
    SearchInfoAlertView()
}