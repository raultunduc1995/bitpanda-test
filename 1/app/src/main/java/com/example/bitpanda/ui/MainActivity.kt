package com.example.bitpanda.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bitpanda.R

class MainActivity : AppCompatActivity() {

    var wallets = listOf<IMPLEMENT_ME>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val filteredWalletList = wallets.filter {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun sortWalletList(wallets: List<IMPLEMENT_ME>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun showWalletList(wallets: List<IMPLEMENT_ME>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}