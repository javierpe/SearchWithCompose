package com.app.ml.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.app.ml.ui.components.ProductSkeletonView

@Composable
fun SkeletonsScreen(
    modifier: Modifier = Modifier,
    products: List<String>
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(20.dp)
    ) {

        items(items = products) {
            ProductSkeletonView()
        }
    }
}