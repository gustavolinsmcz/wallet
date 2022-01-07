package com.gustavolins.wallet.ui.fragment.statement

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavolins.wallet.database.storage.StorageTransaction
import com.gustavolins.wallet.model.TransactionAndCoin
import kotlinx.coroutines.launch

class StatementViewModel@ViewModelInject constructor(val storageTransaction: StorageTransaction
) : ViewModel() {

    val listOperations = MutableLiveData<List<TransactionAndCoin>>()

    fun getTransaction() {
        viewModelScope.launch {
            listOperations.value = storageTransaction.getTransactionAndCoin()
        }
    }

}