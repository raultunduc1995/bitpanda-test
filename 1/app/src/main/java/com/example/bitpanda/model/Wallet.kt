package com.example.bitpanda.model

sealed class Wallet(
    open val id: String,
    open val name: String,
    open var balance: Double,
    open val isDefault: Boolean,
    open val deleted: Boolean,
    val currencyId: String
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

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Wallet)
            return false

        return this.id == other.id &&
                this.name == other.name &&
                this.balance == other.balance &&
                this.isDefault == other.isDefault &&
                this.deleted == other.deleted
    }
}

data class MetalWallet(
    override val id: String = "",
    override val name: String = "",
    override var balance: Double = 0.0,
    override val isDefault: Boolean = false,
    override val deleted: Boolean = false,
    val metalId: String = "",
) : Wallet(id, name, balance, isDefault, deleted, metalId)

data class FiatWallet(
    override val id: String = "",
    override val name: String = "",
    override var balance: Double = 0.0,
    override val isDefault: Boolean = false,
    override val deleted: Boolean = false,
    val fiatId: String = "",
) : Wallet(id, name, balance, isDefault, deleted, fiatId)

data class CryptocoinWallet(
    override val id: String = "",
    override val name: String = "",
    override var balance: Double = 0.0,
    override val isDefault: Boolean = false,
    override val deleted: Boolean = false,
    val cryptocoinId: String = "",
) : Wallet(id, name, balance, isDefault, deleted, cryptocoinId)