package com.gustavolins.wallet.ui.activity.Transition
import android.content.DialogInterface
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.gustavolins.wallet.databinding.ActivityTransactionBinding
import com.gustavolins.wallet.model.Coin
import dagger.hilt.android.AndroidEntryPoint
import android.widget.AdapterView
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.gustavolins.wallet.R
import com.gustavolins.wallet.model.Balance
import com.gustavolins.wallet.model.TransactionAndCoin

@AndroidEntryPoint
class TransactionActivity : AppCompatActivity() {

    private val transactionViewModel : TransactionViewModel by viewModels()
    private lateinit var binding: ActivityTransactionBinding
    private lateinit var fromCoin: Coin
    private lateinit var fromCoinBalance: Balance
    private var toCoin: Coin? = null
    private lateinit var toCoinBalance: Balance
    private lateinit var transactionAndCoin: TransactionAndCoin

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val operation = intent.extras?.get("operation") as Long
        var coin = intent.getSerializableExtra("coin") as Coin

        transactionViewModel.updateQuoteCoin(coin)

        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transactionViewModel.transaction.observe(this, androidx.lifecycle.Observer {
             transactionAndCoin = it!!
             fromCoin = it!!.fromCoin!!
             toCoin = it?.toCoin
             binding.tvCoinFrom.text = "De: " + fromCoin.nameCoin
             binding.tvCoinTo.text = "Para: " + toCoin?.nameCoin
             binding.tvQuoteFrom.text = "[R$ " + fromCoin.currentQuote + "]"
             binding.tvQuoteTo.text = "[R$ " + toCoin?.currentQuote + "]"
             binding.editTextValue.hint = "Digite o valor em " + fromCoin.nameCoin
             transactionViewModel.getAccountAndBalance()
            if (toCoin == null) {
                binding.tvCoinTo.setText(R.string.select)
                transactionViewModel.getCryptocurrencies()
            }
        })

        transactionViewModel.quoteCoin.observe(this, androidx.lifecycle.Observer {
            if (it == null ) {
                alert(R.string.error_loading)
            } else if (!this::transactionAndCoin.isInitialized) {
                coin.currentQuote = it
                transactionViewModel.prepareTransaction(coin, operation)
            } else {
                toCoin?.currentQuote = it
                binding.tvQuoteTo.text = "[R$ " + it + "]"
                transactionAndCoin.toCoin = toCoin
            }
        })

        transactionViewModel.listBalance.observe(this, androidx.lifecycle.Observer {
          for (item in it.balance ) {
              if (item.coin.coinId == fromCoin.coinId) {
                  fromCoinBalance = item.balance
                  binding.tvCoinFromBalance.text = fromCoinBalance.currentBalance.toString()
              }
              if(item.coin.coinId == toCoin?.coinId) {
                  toCoinBalance = item.balance
                  binding.tvCoinToBalance.text = toCoinBalance.currentBalance.toString()
              }
          }
        })

        val inputValue = binding.editTextValueInput
        transactionViewModel.listCryptocurrencies.observe(this, androidx.lifecycle.Observer {
            var listCoin = mutableListOf<Coin>()
            for (coin in it) {
                if (fromCoin.coinId != coin.coinId){
                    listCoin.add(coin)
                }
            }

            val adapter: ArrayAdapter<Coin> = ArrayAdapter<Coin>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                listCoin
            )
            binding.spnFromCoin.adapter = adapter
            binding.spnFromCoin.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(adapterView: AdapterView<*>?, view: View, i: Int, l: Long) {
                    toCoin = adapter.getItem(i)
                        transactionViewModel.updateQuoteCoin(toCoin)
                }
                override fun onNothingSelected(adapterView: AdapterView<*>?) {}
            })
            binding.spnFromCoin.isVisible = true

        })

        val btOperation = binding.btOperation
        btOperation.setOnClickListener{
            transactionAndCoin.transaction.value = inputValue.text.toString().toDouble()
            transactionAndCoin.toCoin = toCoin
            transactionAndCoin.transaction.toCoinId = toCoin?.coinId
            transactionViewModel.runTransiction(transactionAndCoin)
            alert(R.string.success_convert)
        }

        inputValue.doAfterTextChanged{
            btOperation.isEnabled = transactionViewModel.validateValue(it!!, fromCoinBalance.currentBalance)
        }
    }

    fun alert(alert: Int) {
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setMessage(alert)
            dialogBuilder.setCancelable(false)
                .setCancelable(false)
                 .setPositiveButton("OK", DialogInterface.OnClickListener {
                        dialog, id -> finish()
                })
             val alert = dialogBuilder.create()
             alert.setTitle("")
             alert.show()
        }
}