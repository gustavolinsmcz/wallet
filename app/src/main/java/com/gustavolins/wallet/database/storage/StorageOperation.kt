package com.gustavolins.wallet.database.storage

import android.content.Context
import androidx.room.Query
import com.gustavolins.wallet.database.AppDatabase
import com.gustavolins.wallet.database.dao.CoinDao
import com.gustavolins.wallet.database.dao.OperationDao
import com.gustavolins.wallet.model.Client
import com.gustavolins.wallet.model.Operation
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageOperation @Inject constructor(@ApplicationContext private  val context: Context) {

    suspend fun inserOperation(operation: Operation) = withContext(Dispatchers.IO) {
        getDao().insert(operation)
    }


    fun getDao(): OperationDao {
        return AppDatabase.getInstance(context).getOperationDao()
    }

    suspend fun getAll() = withContext(Dispatchers.IO) {
        getDao().getAll()
    }
}
