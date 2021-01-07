package com.example.bitpanda.remote

import com.example.bitpanda.model.*

class DummyWebService {
    fun getCryptoWallets(): List<CryptocoinWallet> {
        return DummyData.dummyCryptoWalletList
    }

    fun getMetalWallets(): List<MetalWallet> {
        return DummyData.dummyMetalWalletList
    }

    fun getFiatWallets(): List<FiatWallet> {
        return DummyData.dummyEURWallet
    }

    fun getCryptocoins(): List<Cryptocoin> {
        return DummyData.cryptocoins
    }

    fun getMetals(): List<Metal> {
        return DummyData.metals
    }

    fun getFiats(): List<Fiat> {
        return DummyData.fiats
    }

    fun getCurrencies(): List<Currency> {
        val currencies = mutableListOf<Currency>()

        currencies.apply {
            addAll(DummyData.cryptocoins)
            addAll(DummyData.metals)
            addAll(DummyData.fiats)
        }

        return currencies
    }

    fun getWallets(): List<Wallet> {
        val wallets = mutableListOf<Wallet>()

        wallets.apply {
            addAll(DummyData.dummyCryptoWalletList)
            addAll(DummyData.dummyEURWallet)
            addAll(DummyData.dummyMetalWalletList)
        }

        return wallets
    }
}