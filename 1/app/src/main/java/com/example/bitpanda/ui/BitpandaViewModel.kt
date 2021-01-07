package com.example.bitpanda.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bitpanda.model.BitpandaData
import com.example.bitpanda.remote.DummyWebService
import com.example.bitpanda.repository.Repository
import com.example.bitpanda.ui.components.FilterSpinner
import java.lang.IllegalStateException

class BitpandaViewModel(private val repository: Repository): ViewModel() {
    private val bitpandaData = MutableLiveData<List<BitpandaData>>()
    val bitpandaDataLiveData
        get() = bitpandaData

    private val _selectedBitpandaData = MutableLiveData<BitpandaData>()
    val selectedBitpandaData: LiveData<BitpandaData>
        get() = _selectedBitpandaData

    fun fetchData() {
        bitpandaData.value = repository.getBitpandaData()
    }

    fun fetchFilteredData(filterOption: FilterSpinner.Option) {
        when (filterOption) {
            FilterSpinner.Option.ALL ->
                fetchData()
            FilterSpinner.Option.FIATS ->
                bitpandaData.value = repository.getWalletsInFiatCurrency()
            FilterSpinner.Option.METALS ->
                bitpandaData.value = repository.getWalletsInMetalCurrency()
            FilterSpinner.Option.CRYPTOCOINS ->
                bitpandaData.value = repository.getWalletsInCryptocoinCurrency()
        }
    }

    fun onBitpandaDataSelected(bitpandaData: BitpandaData) {
        _selectedBitpandaData.value = bitpandaData
    }
}

@Suppress("UNCHECKED_CAST")
class BitpandaViewModelFactory(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BitpandaViewModel::class.java)) {
            val webService = DummyWebService()
            val repository = Repository(webService)

            return BitpandaViewModel(repository) as T
        }
        throw IllegalStateException("Unknown ViewModel class")
    }
}