package com.example.bitpanda.repository

import com.example.bitpanda.model.*
import com.example.bitpanda.remote.DummyWebService

class Repository(val webservice: DummyWebService) {

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
        val bitpandaDataList = fetchBitpandaData()

        return bitpandaDataList.filter { it.currency.symbol == symbol }
    }


    fun findByName(name: String): List<BitpandaData> {
        val bitpandaDataList = fetchBitpandaData()

        return bitpandaDataList.filter { it.currency.name == name }
    }

    fun getBitpandaData(): List<BitpandaData> {
        return fetchBitpandaData()
    }

    fun getWalletsInFiatCurrency(): List<BitpandaData> {
        val bitpandaDataList = fetchBitpandaData()

        return bitpandaDataList.filter { it.currency is Fiat }
    }

    fun getWalletsInMetalCurrency(): List<BitpandaData> {
        val bitpandaDataList = fetchBitpandaData()

        return bitpandaDataList.filter { it.currency is Metal }
    }

    fun getWalletsInCryptocoinCurrency(): List<BitpandaData> {
        val bitpandaDataList = fetchBitpandaData()

        return bitpandaDataList.filter { it.currency is Cryptocoin }
    }

    private fun fetchBitpandaData(): List<BitpandaData> {
        val wallets = webservice.getWallets()
            .filter { !it.deleted }
        val currencies = webservice.getCurrencies()

        return wallets.map { wallet ->
            BitpandaData(wallet, currency = getCurrency(wallet, currencies))
        }
    }
}