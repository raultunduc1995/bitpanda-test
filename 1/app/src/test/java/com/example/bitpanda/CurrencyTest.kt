package com.example.bitpanda

import com.example.bitpanda.utils.CurrencyHelper
import org.junit.Test

class CurrencyTest {
    @Test
    fun `Test total balance computation`() {
        val balance = 100.0
        val pricePerUnit = 2.0
        val result = CurrencyHelper.getTotal(balance, pricePerUnit, precision = 1)

        assert(result == 200.0)
        assert(result.toString() == "200.0")
    }
}