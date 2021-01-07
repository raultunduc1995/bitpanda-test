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
import androidx.recyclerview.widget.*
import com.example.bitpanda.R
import com.example.bitpanda.databinding.FragmentMainBinding
import com.example.bitpanda.model.BitpandaData
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

        adapter = WalletsAdapter()
        binding.walletsList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        binding.walletsList.layoutManager = LinearLayoutManager(context)
        binding.walletsList.adapter = adapter

        viewModel.bitpandaDataLiveData.observe(viewLifecycleOwner, {
            adapter.submitList(it)
        })
        viewModel.fetchData()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class WalletViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val walletTitle: TextView = view.findViewById(R.id.wallet_title)
    val currencyLogo: ImageView = view.findViewById(R.id.currency_logo)
    val balance: TextView = view.findViewById(R.id.balance)
}
class WalletsAdapter : ListAdapter<BitpandaData, WalletViewHolder>(DIFF_CALLBACK) {
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

        holder.walletTitle.text = bitpandaData.wallet.name
        holder.balance.text = StringBuilder()
            .append(bitpandaData.wallet.balance)
            .append(bitpandaData.currency.symbol)
            .toString()
        GlideToVectorYou.init()
            .with(holder.currencyLogo.context)
            .load(Uri.parse(bitpandaData.currency.logo), holder.currencyLogo)
    }
}