package com.example.bitpanda.model

data class Cryptocoin(
    val precision: Int = 4,
    val name: String = "",
    val symbol: String = "",
    val id: String = "",
    val price: Double = 0.0,
    val logo: String = ""
)