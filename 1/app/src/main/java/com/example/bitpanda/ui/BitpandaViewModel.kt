package com.example.bitpanda.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bitpanda.model.BitpandaData
import com.example.bitpanda.remote.DummyWebService
import com.example.bitpanda.repository.Repository
import java.lang.IllegalStateException

class BitpandaViewModel(private val repository: Repository): ViewModel() {
    private val bitpandaData = MutableLiveData<List<BitpandaData>>()
    val bitpandaDataLiveData
        get() = bitpandaData

    fun fetchData() {
        bitpandaData.value = repository.getBitpandaData()
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