package com.example.bitpanda

import com.example.bitpanda.utils.CurrencyHelper
import org.junit.Test

class CurrencyTest {
    @Test
    fun `Test total balance with 5 precision`() {
        val balance = 100.0
        val pricePerUnit = 2.0
        val result = CurrencyHelper.getTotal(balance, pricePerUnit, precision = 5)

        assert(result == 200.0)
        assert(result.toString() == "200.0")
    }

    @Test
    fun `Test total balance with 4 precision`() {
        val balance = 10.456
        val pricePerUnit = 1.245
        val result = CurrencyHelper.getTotal(balance, pricePerUnit, precision = 4)

        assert(result == 13.0177)
    }
}