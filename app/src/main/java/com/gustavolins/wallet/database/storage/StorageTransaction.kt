package com.gustavolins.wallet.database.storage

import android.content.Context
import androidx.room.Query
import com.gustavolins.wallet.database.AppDatabase
import com.gustavolins.wallet.database.dao.CoinDao
import com.gustavolins.wallet.database.dao.OperationDao
import com.gustavolins.wallet.database.dao.TransactionDao
import com.gustavolins.wallet.model.Client
import com.gustavolins.wallet.model.Operation
import com.gustavolins.wallet.model.Transaction
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageTransaction @Inject constructor(@ApplicationContext private  val context: Context) {

    suspend fun inserTransaction(transaction: Transaction ) = withContext(Dispatchers.IO) {
        getDao().insert(transaction)
    }


    fun getDao(): TransactionDao {
        return AppDatabase.getInstance(context).getTransaction()
    }

    suspend fun getTransactionAndCoin() = withContext(Dispatchers.IO) {
        getDao().getTransactionAndCoin()
    }
}
