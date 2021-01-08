package com.example.bitpanda.utils

import java.math.BigDecimal
import java.math.RoundingMode

object CurrencyHelper {
    // Get the Total rounded to decimal precision
    fun getTotal(amount: Double, pricePerUnit: Double, precision: Int): Double {
        val price = amount * pricePerUnit

        return BigDecimal(price)
            .setScale(precision, RoundingMode.HALF_EVEN)
            .toDouble()
    }
}