package com.app.ml.extensions

import java.text.NumberFormat

fun Double.priceToString(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}