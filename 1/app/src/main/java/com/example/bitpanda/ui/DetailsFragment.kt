package com.example.bitpanda.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.bitpanda.R
import com.example.bitpanda.databinding.FragmentDetailsBinding
import com.example.bitpanda.model.ValuableCurrency
import com.example.bitpanda.utils.CurrencyHelper
import com.example.bitpanda.utils.show
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class DetailsFragment : Fragment() {
    private var _binding: FragmentDetailsBinding? = null
    val binding: FragmentDetailsBinding
        get() = _binding!!
    val viewModel by activityViewModels<BitpandaViewModel> {
        BitpandaViewModelFactory()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bitpandaData = viewModel.selectedBitpandaData.value!!
        val (_, currency) = bitpandaData

        showDefaultDetails()
        if (currency is ValuableCurrency) {
            showExchangeIfApplicable()
        }
    }

    private fun showDefaultDetails() {
        val bitpandaData = viewModel.selectedBitpandaData.value!!
        val (wallet, currency) = bitpandaData

        GlideToVectorYou.init()
            .with(context)
            .load(Uri.parse(currency.logo), binding.currencyLogo)
        binding.walletTitle.text = wallet.name
        binding.balance.text = StringBuilder()
            .append(wallet.balance)
            .append(currency.symbol)
            .toString()
        binding.currency.text = currency.name
    }

    private fun showExchangeIfApplicable() {
        val bitpandaData = viewModel.selectedBitpandaData.value!!
        val (wallet, currency) = bitpandaData


        binding.unitPriceLabel.show()
        binding.unitPrice.apply {
            text = StringBuilder()
                .append((currency as ValuableCurrency).price)
                .append(resources.getString(R.string.euro_symbol))
                .toString()
            show()
        }
        binding.totalLabel.show()
        binding.total.apply {
            text = StringBuilder()
                .append(
                    CurrencyHelper.getTotal(
                        amount = wallet.balance,
                        pricePerUnit = (currency as ValuableCurrency).price,
                        precision = currency.precision
                    )
                )
                .append(resources.getString(R.string.euro_symbol))
                .toString()
            show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}