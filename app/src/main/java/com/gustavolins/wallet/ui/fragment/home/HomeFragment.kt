package com.gustavolins.wallet.ui.fragment.home


import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustavolins.wallet.databinding.FragmentHomeBinding
import com.gustavolins.wallet.ui.adapter.BalanceAdapter
import dagger.hilt.android.AndroidEntryPoint

import android.widget.PopupMenu
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.gustavolins.wallet.R
import com.gustavolins.wallet.model.AccountAndBalance
import com.gustavolins.wallet.model.Operation
import com.gustavolins.wallet.ui.activity.Transition.TransactionActivity
import java.io.Serializable


@AndroidEntryPoint
class HomeFragment : Fragment(),  BalanceAdapter.ItemClickListener  {

    private val homeViewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private lateinit var mAdapter: BalanceAdapter
    private lateinit var listOperations: List<Operation>
    private lateinit var accountAndBalance: AccountAndBalance

    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel.getAccountAndBalance()
        homeViewModel.gatOperations()

        activity?.let {
            homeViewModel.listBalance.observe(it) {
                accountAndBalance = it
                mAdapter = BalanceAdapter(context, accountAndBalance.balance)
                val rvBalance = binding.rvBalance;
                rvBalance.setHasFixedSize(true)
                rvBalance.layoutManager =
                    LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                mAdapter.setClickListener(this)
                rvBalance.adapter = mAdapter
                rvBalance.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                mAdapter.notifyDataSetChanged()

            }
        }

        activity?.let {
            homeViewModel.listOperations.observe(it) {
                listOperations = it
            }
        }

        return root
    }

    @Override
    override fun onItemClick(view: View?, position: Int) {

        val popup = PopupMenu(context, binding.rvBalance.getChildAt(position), Gravity.CENTER)
        popup.menu.add(Menu.NONE, -1, 0, mAdapter.getItem(position).coin.nameCoin ).apply {
            isEnabled = false }

        for(operation in listOperations) {
            popup.menu.add(Menu.NONE, operation.operationId!!.toInt(), 0, operation.nameOperation ).apply {
                isEnabled = true }
        }

        popup.getMenuInflater()
            .inflate(R.menu.popup_menu, popup.getMenu())
        popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
            override fun onMenuItemClick(item: MenuItem): Boolean {
                if (accountAndBalance.coinDefault.coinId == mAdapter.getItem(position).coin.coinId) {
                    Toast.makeText(context, R.string.its_not_possible, Toast.LENGTH_SHORT).show()
                } else {
                    val intent = Intent(context, TransactionActivity::class.java)
                    intent.putExtra("operation", item.itemId.toLong())
                    intent.putExtra("coin", mAdapter.getItem(position).coin as Serializable?)
                    startActivity(intent)
                }
                return true
            }
        })
        popup.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getAccountAndBalance()
    }

}