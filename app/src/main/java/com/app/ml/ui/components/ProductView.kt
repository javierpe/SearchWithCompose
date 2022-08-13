package com.app.ml.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.ml.R
import com.app.ml.data.priceToString
import com.app.ml.data.models.search.ProductSearchSearchModel
import com.app.ml.ui.theme.ConditionNew
import com.app.ml.ui.theme.ConditionUsed
import com.app.ml.ui.theme.ShippingColor
import com.app.ml.ui.theme.Typography

const val NEW: String = "new"

@Composable
fun ProductItemView(
    product: ProductSearchSearchModel,
    onProductSelected: () -> Unit
) {
    Box {
        Card(
            shape = RoundedCornerShape(percent = 20),
            modifier = Modifier
                .height(190.dp)
                .defaultMinSize(minHeight = 120.dp)
                .padding(start = 55.dp, end = 16.dp, bottom = 16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        onProductSelected.invoke()
                    }
                    .padding(start = 75.dp, end = 16.dp, top = 16.dp, bottom = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Text(
                    text = if (product.condition == NEW) {
                        stringResource(R.string.product_card_condition_new)
                    } else {
                        stringResource(R.string.product_card_condition_used)
                    },
                    style = Typography.headlineMedium,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .weight(3f)
                        .fillMaxWidth(),
                    color = if (product.condition == NEW) {
                        ConditionNew
                    } else {
                        ConditionUsed
                    },
                    textAlign = TextAlign.End
                )

                Text(
                    text = product.title,
                    style = Typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(4f),
                    color = MaterialTheme.colorScheme.primary
                )

                product.productPrices?.let {
                    it.prices.first().let { item ->
                        if (item.amount < product.price) {
                            Text(
                                text = item.amount.priceToString(),
                                style = Typography.labelSmall,
                                maxLines = 2,
                                modifier = Modifier
                                    .padding(top = 5.dp)
                                    .weight(2f)
                                    .fillMaxWidth(),
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.7f),
                                textDecoration = TextDecoration.LineThrough
                            )
                        }
                    }
                }

                Text(
                    text = product.price.priceToString(),
                    style = Typography.headlineMedium,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .weight(3f)
                        .fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )

                product.shipping?.let {
                    if (it.freeShipping || it.storePickUp) {
                        Text(
                            text = if (it.freeShipping) {
                                stringResource(R.string.product_card_free_shipping)
                            } else if (it.storePickUp) {
                                stringResource(R.string.product_card_pick_up_shipping)
                            } else "",
                            style = Typography.labelSmall,
                            maxLines = 2,
                            modifier = Modifier
                                .padding(top = 5.dp)
                                .weight(3f)
                                .fillMaxWidth(),
                            color = ShippingColor
                        )
                    }
                }
            }
        }

        Card(
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.CenterStart),
            shape = RoundedCornerShape(percent = 20),
            border = BorderStroke(2.dp, MaterialTheme.colorScheme.secondary)
        ) {
            ProductImage(
                modifier = Modifier.size(120.dp),
                id = product.id,
                thumbnail = product.thumbnail
            )
        }

    }
}

@Composable
fun ProductSkeletonView() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.5f))
    )
}
