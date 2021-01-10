package com.example.bitpanda.repository

import com.example.bitpanda.model.*
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
        var fiatWallets = webservice.getFiatWallets()
            .filter { !it.deleted }

        fiatWallets = sortByCurrencyAndBalance(fiatWallets)

        return fiatWallets.map { wallet ->
            BitpandaData(wallet, currency = getFiatCurrency(wallet))
        }
    }

    fun getWalletsInMetalCurrency(): List<BitpandaData> {
        var metalWallets = webservice.getMetalWallets()
            .filter { !it.deleted }

        metalWallets = sortByCurrencyAndBalance(metalWallets)

        return metalWallets.map { wallet ->
            BitpandaData(wallet, currency = getMetalCurrency(wallet))
        }
    }

    fun getWalletsInCryptocoinCurrency(): List<BitpandaData> {
        var cryptocoinWallets = webservice.getCryptoWallets()
            .filter { !it.deleted }

        cryptocoinWallets = sortByCurrencyAndBalance(cryptocoinWallets)

        return cryptocoinWallets.map { wallet ->
            BitpandaData(wallet, currency = getCryptocoinCurrency(wallet))
        }
    }

    private fun getFiatCurrency(wallet: FiatWallet): Fiat {
        var fiats = webservice.getFiats()

        return fiats.first { it.id == wallet.fiatId }
    }

    private fun getMetalCurrency(wallet: MetalWallet): Metal {
        val metals = webservice.getMetals()

        return metals.first { it.id == wallet.metalId }
    }

    private fun getCryptocoinCurrency(wallet: CryptocoinWallet): Cryptocoin {
        val cryptocoins = webservice.getCryptocoins()

        return cryptocoins.first { it.id == wallet.cryptocoinId }
    }

    private fun <T : Wallet> sortByCurrencyAndBalance(wallets: List<T>): List<T> {
        val walletsToSort = wallets.toMutableList()

        for (i in walletsToSort.indices) {
            for (j in i + 1 until walletsToSort.size) {
                val iWallet = walletsToSort[i]
                val jWallet = walletsToSort[j]
                val isNotSorted: Boolean =
                    iWallet.currencyId == jWallet.currencyId &&
                            iWallet.balance > jWallet.balance

                if (isNotSorted) {
                    for (k in j downTo i + 1)
                        walletsToSort[k] = walletsToSort[k - 1]
                    walletsToSort[i] = jWallet
                }
            }
        }

        return walletsToSort
    }
}