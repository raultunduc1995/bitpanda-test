package com.example.bitpanda.ui.components

import android.content.Context
import android.content.res.Resources
import android.util.AttributeSet
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bitpanda.R

class FilterSpinner : AppCompatSpinner, AdapterView.OnItemSelectedListener {
    private val _filterOptionLiveData = MutableLiveData(Option.ALL)
    val filterOption: LiveData<Option>
        get() = _filterOptionLiveData

    constructor(context: Context): this(context, null)
    constructor(context: Context, mode: Int): this(context, null, androidx.appcompat.R.attr.spinnerStyle, mode)
    constructor(context: Context, attributeSet: AttributeSet?): this(context, attributeSet, androidx.appcompat.R.attr.spinnerStyle)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int): this(context, attributeSet, defStyleAttr, -1)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, mode: Int): this(context, attributeSet, defStyleAttr, mode, null)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, mode: Int, popupTheme: Resources.Theme?): super(context, attributeSet, defStyleAttr, mode, popupTheme) {
        ArrayAdapter.createFromResource(
            context,
            R.array.wallet_filters,
            android.R.layout.simple_spinner_item
        ).also { arrayAdapter ->
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            this.adapter = arrayAdapter
            this.onItemSelectedListener = this@FilterSpinner
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        val selectedItem: String = getItemAtPosition(pos) as String
        _filterOptionLiveData.value = Option.getOption(selectedItem)
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        // Do nothing
    }

    enum class Option(val value: String) {
        ALL("All"),
        FIATS("Fiats"),
        METALS("Metals"),
        CRYPTOCOINS("Cryptocoins");

        companion object {
            fun getOption(value: String): Option =
                values().first { it.value == value }
        }
    }
}