package com.app.ml.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.pluralStringResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.app.ml.R
import com.app.ml.data.ProductImageMode
import com.app.ml.data.models.productDetail.ProductDetailModel
import com.app.ml.data.priceToString
import com.app.ml.ui.components.BuyButton
import com.app.ml.ui.components.NEW
import com.app.ml.ui.components.ProductImage
import com.app.ml.ui.theme.BuyColor
import com.app.ml.ui.theme.Typography

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ProductDetailContent(
    product: ProductDetailModel
) {
    Column {
        LazyColumn(
            modifier = Modifier
                .padding(16.dp)
                .weight(2f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {

            item {
                ProductImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(360.dp),
                    id = product.id,
                    mode = ProductImageMode.PRODUCT_SLIDER,
                    images = product.pictures
                )
            }

            item {
                Text(
                    text = if (product.condition == NEW) {
                        pluralStringResource(
                            id = R.plurals.product_card_condition_new_plus_sell,
                            count = product.soldQuantity,
                            product.soldQuantity
                        )
                    } else {
                        pluralStringResource(
                            id = R.plurals.product_card_condition_used_plus_sell,
                            count = product.soldQuantity,
                            product.soldQuantity
                        )
                    },
                    style = Typography.headlineSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f)
                )
            }

            item {
                Text(
                    text = product.title,
                    style = Typography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            item {
                Column {
                    if (product.originalPrice > 0) {
                        Text(
                            text = product.originalPrice.priceToString(),
                            style = Typography.bodyLarge,
                            modifier = Modifier.fillMaxWidth(),
                            color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.7f),
                            textDecoration = TextDecoration.LineThrough
                        )
                    }

                    Text(
                        text = product.price.priceToString(),
                        style = Typography.titleLarge,
                        modifier = Modifier.fillMaxWidth(),
                        color = MaterialTheme.colorScheme.secondary
                    )
                }
            }

            item {
                Text(
                    text = if (product.shippingModel.freeShipping) {
                        stringResource(R.string.product_card_free_shipping)
                    } else if (product.shippingModel.storePickUp) {
                        stringResource(R.string.product_card_pick_up_shipping)
                    } else "",
                    style = Typography.labelSmall,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    color = BuyColor
                )
            }

            item {
                Text(
                    text = if (product.shippingModel.freeShipping) {
                        stringResource(R.string.product_card_free_shipping)
                    } else if (product.shippingModel.storePickUp) {
                        stringResource(R.string.product_card_pick_up_shipping)
                    } else "",
                    style = Typography.labelSmall,
                    maxLines = 2,
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    color = BuyColor
                )
            }
        }


        BuyButton(
            modifier = Modifier.padding(16.dp),
            text = "Comprar ahora"
        ) {

        }
    }
}

