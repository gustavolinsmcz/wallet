package com.gustavolins.wallet.database.storage

import android.content.Context
import com.gustavolins.wallet.database.AppDatabase
import com.gustavolins.wallet.database.dao.AccountDao
import com.gustavolins.wallet.database.dao.ClientDao
import com.gustavolins.wallet.model.Account
import com.gustavolins.wallet.model.Client
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageAccount @Inject constructor(@ApplicationContext private  val context: Context){

    suspend fun insertAccount(account: Account) = withContext(Dispatchers.IO) {
        getDao().insert(account)
    }

    suspend fun getAccountAndBalance() = withContext(Dispatchers.IO) {
        getDao().getAccountAndBalance()
    }

    suspend fun getAccount() = withContext(Dispatchers.IO) {
        getDao().getAccount()
    }
    fun getDao(): AccountDao {
        return AppDatabase.getInstance(context).getAccountDao()
    }

}