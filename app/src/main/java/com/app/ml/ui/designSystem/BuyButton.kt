package com.app.ml.ui.designSystem

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.ml.ui.theme.BuyColor
import com.app.ml.ui.theme.Typography

@Composable
fun BuyButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .testTag("buy_button")
            .fillMaxWidth()
            .height(60.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(BuyColor)
            .clickable { onClick.invoke() }
    ) {
        Text(
            text = text,
            style = Typography.displayMedium,
            color = Color.White,
            modifier = Modifier
                .align(Alignment.Center)
                .testTag("buy_button_text")
        )
    }
}

@Preview
@Composable
fun PreviewBuyButton() {
    BuyButton(
        modifier = Modifier,
        text = "Comprar ahora"
    ) {

    }
}