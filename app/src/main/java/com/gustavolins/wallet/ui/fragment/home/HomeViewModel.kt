package com.gustavolins.wallet.ui.fragment.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gustavolins.wallet.database.storage.*
import com.gustavolins.wallet.model.AccountAndBalance
import com.gustavolins.wallet.model.Operation
import kotlinx.coroutines.launch

class HomeViewModel@ViewModelInject constructor(val storageAccount: StorageAccount,
                                                val storageOperation: StorageOperation
) :  ViewModel() {

    val listBalance = MutableLiveData<AccountAndBalance>()
    val listOperations = MutableLiveData<List<Operation>>()


    fun getAccountAndBalance() {
        viewModelScope.launch {
            listBalance.value = storageAccount.getAccountAndBalance()
        }
    }

    fun gatOperations() {
        viewModelScope.launch {
            listOperations.value = storageOperation.getAll()
        }
    }
}