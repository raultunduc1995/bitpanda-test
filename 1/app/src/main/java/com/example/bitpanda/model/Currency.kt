package com.example.bitpanda.model

sealed class Currency(
    open val precision: Int,
    open val name: String,
    open val symbol: String,
    open val id: String,
    open val logo: String
)

data class Cryptocoin(
    override val precision: Int = 4,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String = "",
    val price: Double = 0.0,
) : Currency(precision, name, symbol, id, logo)

data class Fiat(
    override val precision: Int = 2,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String = ""
) : Currency(precision, name, symbol, id, logo)

data class Metal(
    override val precision: Int = 3,
    override val name: String = "",
    override val symbol: String = "",
    override val id: String = "",
    override val logo: String = "",
    val price: Double = 0.0,
) : Currency(precision, name, symbol, id, logo)