package com.example.bitpanda

import com.example.bitpanda.model.Cryptocoin
import com.example.bitpanda.model.Fiat
import com.example.bitpanda.model.Metal
import com.example.bitpanda.remote.DummyWebService
import com.example.bitpanda.repository.Repository
import org.junit.Before
import org.junit.Test

class RepositoryTest {
    lateinit var repository: Repository

    @Before
    fun setup() {
        val webService = DummyWebService()
        repository = Repository(webService)
    }

    @Test
    fun `Test searching for symbol`() {
        assert(repository.findBySymbol("EUR").isNotEmpty())
        assert(repository.findBySymbol("XAU").size == 2)
        assert(repository.findBySymbol("unknown").isEmpty())
    }

    @Test
    fun `Test searching for name`() {
        assert(repository.findByName("Palladium").isNotEmpty())
        assert(repository.findByName("Gold").size == 2)
        assert(repository.findByName("unknown").isEmpty())
    }

    @Test
    fun `Test filter by fiat currency`() {
        //Before
        val fiatWallets = repository.getWalletsInFiatCurrency()

        //Then
        assert(fiatWallets.all { (_, currency) -> currency is Fiat })
    }

    @Test
    fun `Test filter by metal currency`() {
        //Before
        val metalWallets = repository.getWalletsInMetalCurrency()

        //Then
        assert(metalWallets.all { (_, currency) -> currency is Metal })
    }

    @Test
    fun `Test filter by cryptocoin currency`() {
        //Before
        val cryptoWallets = repository.getWalletsInCryptocoinCurrency()

        //Then
        assert(cryptoWallets.all { (_, currency) -> currency is Cryptocoin })
    }

    @Test
    fun `Test bitpanda data creation`() {
        //Before
        val bitpandaData = repository.getBitpandaData()

        //When
        val euroWallet = bitpandaData.firstOrNull { it.wallet.name.contains("EUR Wallet") }
        val cryptoWallet = bitpandaData.firstOrNull { it.wallet.name.contains("Ripple") }

        //Then
        assert(euroWallet != null)
        assert(euroWallet?.currency?.name == "Euro")
        assert(euroWallet?.currency?.symbol == "EUR")
        assert(cryptoWallet != null)
        assert(cryptoWallet?.currency?.name == "Ripple")
        assert(cryptoWallet?.currency?.symbol == "XRP")
    }

    @Test
    fun `Test if bitpanda data is sorted`() {
        //Before
        val bitpandaData = repository.getBitpandaData()

        //Then
        for (i in 0 until bitpandaData.size - 1) {
            val (currentDataWallet, currentDataCurrency) = bitpandaData[i]
            val (nextDataWallet, nextDataCurrency) = bitpandaData[i + 1]

            if (
                currentDataCurrency.javaClass.name == nextDataCurrency.javaClass.name &&
                        currentDataCurrency.id == nextDataCurrency.id
            ) {
                assert(currentDataWallet.balance < nextDataWallet.balance)
            }
        }
    }
}