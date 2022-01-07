package com.gustavolins.wallet.ui.activity.Transition

import android.text.Editable
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavolins.wallet.database.storage.StorageAccount
import com.gustavolins.wallet.database.storage.StorageCoin
import com.gustavolins.wallet.model.*
import com.gustavolins.wallet.repository.AppRepository
import com.gustavolins.wallet.usecase.TransactionUseCase
import kotlinx.coroutines.launch

class TransactionViewModel @ViewModelInject constructor(val transactionUseCase: TransactionUseCase,
                                                       val storageAccount: StorageAccount,
                                                       val storageCoin: StorageCoin
) : ViewModel() {

    val transaction = MutableLiveData<TransactionAndCoin?>()
    val listBalance = MutableLiveData<AccountAndBalance>()
    val listCoins = MutableLiveData<List<Coin>>()
    val listCryptocurrencies = MutableLiveData<List<Coin>>()
    val quoteCoin = MutableLiveData<Double?>()

    fun prepareTransaction(coin : Coin,  operation: Long) {
        viewModelScope.launch {
            transaction.value = transactionUseCase.prepareOperation(coin, operation)
        }
    }

     fun getAccountAndBalance() {
        viewModelScope.launch {
            listBalance.value = storageAccount.getAccountAndBalance()
        }
    }

    fun runTransiction(transactionAndCoin: TransactionAndCoin) {
        viewModelScope.launch {
            transactionUseCase.runTransaction(transactionAndCoin)
        }
    }

    fun validateValue(editable: Editable, fromCoinBalance : Double ) : Boolean {
       return  transactionUseCase.validateValue(editable, fromCoinBalance)
    }

    fun getCryptocurrencies() {
        viewModelScope.launch {
            listCryptocurrencies.value = storageCoin.getCryptocurrencies()
        }
    }

    fun updateQuoteCoin(coin : Coin?) {
        viewModelScope.launch {
            quoteCoin.value = transactionUseCase.updateQuoteCoin(coin)
        }
    }



}