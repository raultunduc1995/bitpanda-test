package com.example.bitpanda.ui

import androidx.lifecycle.MutableLiveData
import com.example.bitpanda.model.BitpandaData
import com.example.bitpanda.repository.Repository

class BitpandaViewModel(private val repository: Repository) {
    private val bitpandaData = MutableLiveData<List<BitpandaData>>()
    val bitpandaDataLiveData
        get() = bitpandaData

    fun fetchData() {
        bitpandaData.value = repository.getBitpandaData()
    }
}