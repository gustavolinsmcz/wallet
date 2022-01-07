package com.gustavolins.wallet.database.storage

import android.content.Context
import com.gustavolins.wallet.database.AppDatabase
import com.gustavolins.wallet.database.dao.BalanceDao
import com.gustavolins.wallet.model.Balance
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageBalance @Inject constructor(@ApplicationContext private  val context: Context){
    suspend fun inserBalance(Balance: Balance) = withContext(Dispatchers.IO) {
        getDao().insert(Balance)
    }

    suspend fun updateBalance(idCoin : Long, value: Double) = withContext(Dispatchers.IO) {
        getDao().updateBalance(idCoin, value)
    }

    fun getDao(): BalanceDao {
        return AppDatabase.getInstance(context).getBalanceDao()
    }
}