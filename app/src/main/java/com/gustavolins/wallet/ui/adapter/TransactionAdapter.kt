package com.gustavolins.wallet.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gustavolins.wallet.R
import com.gustavolins.wallet.model.TransactionAndCoin


class TransactionAdapter internal constructor(context: Context?, data: List<TransactionAndCoin>) :
    RecyclerView.Adapter<TransactionAdapter.ViewHolder>() {
    private val mData: List<TransactionAndCoin>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mData[position]
        holder.titleTextView.text =  item.fromCoin.nameCoin + " -> " + item.toCoin?.nameCoin
        holder.subTitleTextView.text = "$ " + item.transaction.value.toString() +" -> $ " +  item.transaction.valueTo.toString() + ")"
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var titleTextView: TextView
        var subTitleTextView: TextView

        override fun onClick(view: View) {
            if (mClickListener != null) mClickListener!!.onItemClick(view, adapterPosition)
        }

        init {
            titleTextView = itemView.findViewById(R.id.title)
            subTitleTextView = itemView.findViewById(R.id.tvSubTitle)
            itemView.setOnClickListener(this)
        }
    }

    fun getItem(id: Int): TransactionAndCoin {
        return mData[id]
    }

    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }
}
