package com.example.bitpanda.remote

import com.example.bitpanda.model.*

object DummyData {
        val dummyMetalWalletList = listOf(
            MetalWallet(
                id = "1",
                name = "Gold Wallet 1",
                balance = 133.729,
                isDefault = true,
                metalId = "4",
                deleted = false
            ),
            MetalWallet(
                id = "2",
                name = "Gold Wallet 2",
                balance = 2043.4340,
                isDefault = false,
                metalId = "4",
                deleted = false
            ),
            MetalWallet(
                id = "2",
                name = "Test Palladium Wallet",
                balance = 200.0,
                isDefault = false,
                metalId = "5",
                deleted = false
            )
        )

        val dummyCryptoWalletList = listOf(
            CryptocoinWallet(
                id = "1",
                name = "Test BTC Wallet",
                balance = 133.7,
                isDefault = false,
                cryptocoinId = "1",
                deleted = false
            ),
            CryptocoinWallet(
                id = "2",
                name = "Test BTC Wallet 2",
                balance = 0.0,
                isDefault = false,
                cryptocoinId = "1",
                deleted = true
            ),
            CryptocoinWallet(
                id = "3",
                name = "Test BEST Wallet",
                balance = 1032.23,
                isDefault = false,
                cryptocoinId = "2",
                deleted = false
            ),
            CryptocoinWallet(
                id = "4",
                name = "Test Ripple Wallet",
                balance = 2304.04,
                isDefault = false,
                cryptocoinId = "3",
                deleted = false
            )
        )

        val dummyEURWallet = listOf(
            FiatWallet(
                id = "1",
                name = "EUR Wallet",
                fiatId = "1",
                balance = 400.0,
                isDefault = false,
                deleted = false
            ),
            FiatWallet(
                id = "2",
                name = "CHF Wallet",
                fiatId = "2",
                balance = 0.0,
                isDefault = false,
                deleted = false
            )
        )

        val cryptocoins: List<Cryptocoin> = listOf(
            Cryptocoin(
                name = "Bitcoin",
                symbol = "BTC",
                id = "1",
                price = 9000.0,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/btc.svg"
            ),
            Cryptocoin(
                name = "Bitpanda Ecosystem Token",
                symbol = "BEST",
                id = "2",
                price = 0.03,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/best.svg"
            ),
            Cryptocoin(
                name = "Ripple",
                symbol = "XRP",
                id = "3",
                price = 0.2119,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xrp.svg"
            )
        )

        val fiats: List<Fiat> = listOf(
            Fiat(
                name = "Euro",
                symbol = "EUR",
                id = "1",
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/usd.svg"
            ),
            Fiat(
                name = "Swiss Franc",
                symbol = "CHF",
                id = "2",
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/fiat/chf.svg"
            )
        )

        val metals: List<Metal> = listOf(
            Metal(
                name = "Gold",
                symbol = "XAU",
                id = "4",
                price = 45.14,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xau.svg"
            ),
            Metal(
                name = "Palladium",
                symbol = "XPD",
                id = "5",
                price = 70.40,
                logo = "https://bitpanda-assets.s3-eu-west-1.amazonaws.com/static/cryptocoin/xpd.svg"
            )
        )
}