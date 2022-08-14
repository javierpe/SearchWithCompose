package com.app.ml.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ml.db.entities.RecentSearchEntity

@Composable
fun RecentSearchScreen(
    modifier: Modifier,
    list: List<RecentSearchEntity>,
    onRecentSearchClick: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary),
        contentPadding = PaddingValues(10.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        items(items = list) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onRecentSearchClick.invoke(it.search)
                    },
                text = it.search,
                color = MaterialTheme.colorScheme.secondary
            )

            if (list.indexOf(it) < list.size - 1) {
                Box(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .height(1.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(
                            MaterialTheme.colorScheme.secondary.copy(alpha = 0.1f)
                        )
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PreviewRecentSearchScreen() {
    RecentSearchScreen(
        modifier = Modifier,
        list = listOf(
            RecentSearchEntity("Apple"),
            RecentSearchEntity("Samsung"),
            RecentSearchEntity("Nokia"),
            RecentSearchEntity("Xiaomi"),
        )
    ) { }
}