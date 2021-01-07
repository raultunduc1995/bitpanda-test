package com.example.bitpanda.model

data class BitpandaData(val wallet: Wallet, val currency: Currency) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is BitpandaData)
            return false

        return this.wallet == other.wallet && this.currency == other.currency
    }
}