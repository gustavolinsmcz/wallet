package com.gustavolins.wallet.database.storage

import android.content.Context
import com.gustavolins.wallet.database.AppDatabase
import com.gustavolins.wallet.database.dao.CoinDao
import com.gustavolins.wallet.model.Coin
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class StorageCoin @Inject constructor(@ApplicationContext private  val context: Context){

    suspend fun insertCoin(coin: Coin) = withContext(Dispatchers.IO) {
        getDao().insert(coin)
    }

    fun getDao(): CoinDao {
        return AppDatabase.getInstance(context).getCoinDao()
    }

    suspend fun getCryptocurrencies() = withContext(Dispatchers.IO) {
        getDao().getCryptocurrencies()
    }

    suspend fun getAll() = withContext(Dispatchers.IO) {
        getDao().getAll()
    }

    suspend fun update(quote : Double?, coinId : Long?) = withContext(Dispatchers.IO) {
        getDao().update(quote, coinId)
    }


}