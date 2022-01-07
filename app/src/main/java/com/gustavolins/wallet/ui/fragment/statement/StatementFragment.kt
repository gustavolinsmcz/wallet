package com.gustavolins.wallet.ui.fragment.statement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.gustavolins.wallet.databinding.FragmentStatementBinding
import com.gustavolins.wallet.ui.adapter.TransactionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class StatementFragment : Fragment() {

    private var _binding: FragmentStatementBinding? = null
    private val statementViewModel: StatementViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        _binding = FragmentStatementBinding.inflate(inflater, container, false)
        val root: View = binding.root
        statementViewModel.getTransaction()
        activity?.let {
            statementViewModel.listOperations.observe(viewLifecycleOwner, Observer {
                val mAdapter = TransactionAdapter(context, it)
                val rvStatement = binding.rvStatement;
                rvStatement.setHasFixedSize(true)
                rvStatement.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                rvStatement.adapter = mAdapter
                rvStatement.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
                mAdapter.notifyDataSetChanged()
            })
        }


        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}