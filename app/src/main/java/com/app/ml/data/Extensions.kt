package com.app.ml.data

import java.text.NumberFormat

fun Double.priceToString(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}