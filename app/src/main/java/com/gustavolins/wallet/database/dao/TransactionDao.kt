package com.gustavolins.wallet.database.dao

import androidx.room.*
import com.gustavolins.wallet.model.TransactionAndCoin

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(transaction: com.gustavolins.wallet.model.Transaction): Long


    @Query("SELECT DISTINCT * FROM `Transaction`")
    fun getAll(): List<com.gustavolins.wallet.model.Transaction>

    @Transaction
    @Query("SELECT * FROM `Transaction`")
    fun getTransactionAndCoin(): List<TransactionAndCoin>

}
