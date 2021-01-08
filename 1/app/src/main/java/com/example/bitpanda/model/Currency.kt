package com.example.bitpanda.model

sealed class Currency(
    open val precision: Int,
    open val name: String,
    open val symbol: String,
    open val id: String,
    open val logo: String
) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Currency)
            return false

        return this.id == other.id &&
                this.name == other.name &&
                this.symbol == other.symbol &&
                this.logo == other.logo &&
                this.precision == other.precision
    }
}

sealed class ValuableCurrency(
    override val precision: Int,
    override val name: String,
    override val symbol: String,
    override val id: String,
    override val logo: String,
    open val price: Double,
) : Currency(precision, name, symbol, id, logo)

data class Fiat(
    override val precision: Int = 2,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String = ""
) : Currency(precision, name, symbol, id, logo)

data class Cryptocoin(
    override val precision: Int = 4,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String = "",
    override val price: Double = 0.0,
) : ValuableCurrency(precision, name, symbol, id, logo, price)

data class Metal(
    override val precision: Int = 3,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String = "",
    override val price: Double = 0.0,
) : ValuableCurrency(precision, name, symbol, id, logo, price)