package com.example.bitpanda.model

data class MetalWallet(
    val id: String = "",
    val metalId: String = "",
    val isDefault: Boolean = false,
    val balance: Double = 0.0,
    val deleted: Boolean = false,
    val name: String = ""
) {

    //todo implement me
    fun reduceBalance(amount: Double) {
    }

    //todo implement me
    fun addBalance(amount: Double) {
    }


}