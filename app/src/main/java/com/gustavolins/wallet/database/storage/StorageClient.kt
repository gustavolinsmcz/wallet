package com.gustavolins.wallet.database.storage

import android.content.Context
import com.gustavolins.wallet.database.AppDatabase
import com.gustavolins.wallet.database.dao.ClientDao
import com.gustavolins.wallet.model.Client
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageClient @Inject constructor(@ApplicationContext private  val context: Context){

    suspend fun inserClient(client: Client) = withContext(Dispatchers.IO) {
        getDao().insert(client)
    }

    fun getDao(): ClientDao {
       return AppDatabase.getInstance(context).getClientDao()
    }

    suspend fun getClient() = withContext(Dispatchers.IO) {
        getDao().getClient()
    }

}