package com.example.bitpanda.repository

import com.example.bitpanda.model.*
import com.example.bitpanda.remote.DummyWebService

class Repository(val webservice: DummyWebService) {
    private val bitpandaDatas = mutableListOf<BitpandaData>()

    init {
        val wallets = webservice.getWallets()
        val currencies = webservice.getCurrencies()

        wallets.forEach { wallet ->
            bitpandaDatas.add(
                BitpandaData(wallet, currency = getCurrency(wallet, currencies))
            )
        }
    }

    private fun getCurrency(
        wallet: Wallet,
        currencies: List<Currency>
    ) = when (wallet) {
        is MetalWallet ->
            currencies.first { it is Metal && wallet.metalId == it.id }
        is FiatWallet ->
            currencies.first { it is Fiat && wallet.fiatId == it.id }
        is CryptocoinWallet ->
            currencies.first { it is Cryptocoin && wallet.cryptocoinId == it.id }
    }

    fun findBySymbol(symbol: String): List<BitpandaData> {
        return bitpandaDatas.filter { it.currency.symbol == symbol }
    }


    fun findByName(name: String): List<BitpandaData> {
        return bitpandaDatas.filter { it.currency.name == name }
    }

    fun getBitpandaData(): List<BitpandaData> {
        return bitpandaDatas
    }
}