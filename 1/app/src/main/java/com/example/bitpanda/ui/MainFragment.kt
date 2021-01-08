package com.example.bitpanda.ui

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.*
import com.example.bitpanda.R
import com.example.bitpanda.databinding.FragmentMainBinding
import com.example.bitpanda.model.BitpandaData
import com.example.bitpanda.model.Metal
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    val binding: FragmentMainBinding
        get() = _binding!!

    val viewModel: BitpandaViewModel by activityViewModels {
        BitpandaViewModelFactory()
    }
    lateinit var adapter: WalletsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWalletsList()
        binding.filterSpinner.filterOption.observe(viewLifecycleOwner, viewModel::fetchFilteredData)
        viewModel.bitpandaDataLiveData.observe(viewLifecycleOwner, adapter::submitList)
        viewModel.fetchData()
    }

    private fun initWalletsList() {
        adapter = WalletsAdapter(::showWalletDetails)
        binding.walletsList.apply {
            addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            layoutManager = LinearLayoutManager(context)
            adapter = this@MainFragment.adapter
        }
    }

    private fun showWalletDetails(bitpandaData: BitpandaData) {
        val navController = view?.findNavController()

        viewModel.onBitpandaDataSelected(bitpandaData)
        navController?.navigate(R.id.action_mainFragment_to_detailsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class WalletsAdapter(val callback: (BitpandaData) -> Unit) :
    ListAdapter<BitpandaData, WalletsAdapter.WalletViewHolder>(DIFF_CALLBACK) {
    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BitpandaData>() {
            override fun areItemsTheSame(oldItem: BitpandaData, newItem: BitpandaData): Boolean {
                return oldItem.wallet.id == newItem.wallet.id
            }

            override fun areContentsTheSame(oldItem: BitpandaData, newItem: BitpandaData): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WalletViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_wallet, parent, false)

        return WalletViewHolder(view)
    }

    override fun onBindViewHolder(holder: WalletViewHolder, position: Int) {
        val bitpandaData = getItem(position)
        val (wallet, currency) = bitpandaData

        holder.walletTitle.text = wallet.name
        holder.balance.text = getBalanceDescription(bitpandaData)
        GlideToVectorYou.init()
            .with(holder.currencyLogo.context)
            .load(Uri.parse(currency.logo), holder.currencyLogo)
        holder.itemView.setOnClickListener { callback.invoke(bitpandaData) }
    }

    private fun getBalanceDescription(bitpandaData: BitpandaData): String {
        val (wallet, currency) = bitpandaData
        val balance = StringBuilder()
            .append(wallet.balance)
            .append(currency.symbol)

        if (currency is Metal)
            balance.append(" (${currency.name})")

        return balance.toString()
    }

    inner class WalletViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val walletTitle: TextView = view.findViewById(R.id.wallet_title)
        val currencyLogo: ImageView = view.findViewById(R.id.currency_logo)
        val balance: TextView = view.findViewById(R.id.balance)
    }
}