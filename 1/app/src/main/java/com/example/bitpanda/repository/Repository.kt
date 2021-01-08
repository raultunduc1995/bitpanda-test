package com.example.bitpanda.repository

import com.example.bitpanda.model.*
import com.example.bitpanda.model.Currency
import com.example.bitpanda.remote.DummyWebService
import java.util.*

class Repository(val webservice: DummyWebService) {
    fun findBySymbol(symbol: String): List<BitpandaData> {
        val bitpandaDataList = getBitpandaData()

        return bitpandaDataList.filter {
            it.currency
                .symbol
                .toLowerCase(Locale.getDefault())
                .contains(symbol.toLowerCase(Locale.getDefault()))
        }
    }

    fun findByName(name: String): List<BitpandaData> {
        val bitpandaDataList = getBitpandaData()

        return bitpandaDataList.filter {
            it.currency
                .name
                .toLowerCase(Locale.getDefault())
                .contains(name.toLowerCase(Locale.getDefault()))
        }
    }

    fun getBitpandaData(): List<BitpandaData> {
        val fiatWallets = getWalletsInFiatCurrency()
        val cryptocoinWallets = getWalletsInCryptocoinCurrency()
        val metalWallets = getWalletsInMetalCurrency()
        val wallets = mutableListOf<BitpandaData>()

        return wallets.apply {
            addAll(fiatWallets)
            addAll(cryptocoinWallets)
            addAll(metalWallets)
        }
    }

    fun getWalletsInFiatCurrency(): List<BitpandaData> {
        val fiatWallets = webservice.getFiatWallets().filter { !it.deleted }

        return fiatWallets.map { wallet ->
            BitpandaData(wallet, currency = getCurrency(wallet))
        }
    }

    fun getWalletsInMetalCurrency(): List<BitpandaData> {
        val metalWallets = webservice.getMetalWallets().filter { !it.deleted }

        return metalWallets.map { wallet ->
            BitpandaData(wallet, currency = getCurrency(wallet))
        }
    }

    fun getWalletsInCryptocoinCurrency(): List<BitpandaData> {
        val cryptocoinWallets = webservice.getCryptoWallets().filter { !it.deleted }

        return cryptocoinWallets.map { wallet ->
            BitpandaData(wallet, currency = getCurrency(wallet))
        }
    }

    private fun getCurrency(wallet: Wallet): Currency {
        val currencies = webservice.getCurrencies()

        return when (wallet) {
            is MetalWallet ->
                currencies.first { it is Metal && wallet.metalId == it.id }
            is FiatWallet ->
                currencies.first { it is Fiat && wallet.fiatId == it.id }
            is CryptocoinWallet ->
                currencies.first { it is Cryptocoin && wallet.cryptocoinId == it.id }
        }
    }
}