package com.example.bitpanda.remote

import com.example.bitpanda.model.*

class Time {
    val date_iso8601: String = ""
    val unix: String = ""
}

class Contact {

    var id: String = ""
    var data: String = ""
    var last_used: Time = Time()
}

class ContactsComponent {
    /**
     * in the live code this is set after the view loaded; you can assume that this won't be null or empty
     * you can mock or change this if you want
     */
    var contacts: List<Contact>? = null

    /**
     * todo : returned list must
     *  1. hold only unique entries (data NOT id)
     *  2. hold max three entries
     *  3. sorted by "last_used" (if you use a custom sort, i'd suggest to use the unix timestamp)
     */
    fun getRecentContacts(): List<Contact> =
        contacts?.distinctBy { it.data }
            ?.sortedByDescending { it.last_used.unix }
            ?.take(3)
            ?: emptyList()
}

class DummyWebService {

    fun getCryptoWallets(): List<CryptocoinWallet> {
    }

    fun getMetalWallets(): List<MetalWallet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getFiatWallets(): List<FiatWallet> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getCryptocoins(): List<Cryptocoin> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getMetals(): List<Metal> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getFiats(): List<Fiat> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun getCurrencies(): List<IMPLEMENT_ME> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}