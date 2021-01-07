package com.example.bitpanda.model

sealed class Wallet(
    open val id: String,
    open val name: String,
    open var balance: Double,
    open val isDefault: Boolean,
    open val deleted: Boolean,
) {
    //todo implement me
    fun reduceBalance(amount: Double) {
        var newBalance = balance - amount

        if (newBalance < 0)
            newBalance = 0.0

        this.balance = newBalance
    }

    //todo implement me
    fun addBalance(amount: Double) {
        this.balance += amount
    }
}

data class MetalWallet(
    override val id: String = "",
    override val name: String = "",
    override var balance: Double = 0.0,
    override val isDefault: Boolean = false,
    override val deleted: Boolean = false,
    val metalId: String = "",
) : Wallet(id, name, balance, isDefault, deleted)

data class FiatWallet(
    override val id: String = "",
    override val name: String = "",
    override var balance: Double = 0.0,
    override val isDefault: Boolean = false,
    override val deleted: Boolean = false,
    val fiatId: String = "",
) : Wallet(id, name, balance, isDefault, deleted)

data class CryptocoinWallet(
    override val id: String = "",
    override val name: String = "",
    override var balance: Double = 0.0,
    override val isDefault: Boolean = false,
    override val deleted: Boolean = false,
    val cryptocoinId: String = "",
) : Wallet(id, name, balance, isDefault, deleted)