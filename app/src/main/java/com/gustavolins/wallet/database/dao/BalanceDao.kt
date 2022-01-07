package com.gustavolins.wallet.database.dao

import androidx.room.*
import com.gustavolins.wallet.model.Balance
import com.gustavolins.wallet.model.ClientAndAccount
import com.gustavolins.wallet.model.Coin

@Dao
interface BalanceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Balance: Balance?): Long

    @Query("SELECT DISTINCT * FROM Balance")
    fun getAll(): List<Balance?>

    @Query("DELETE FROM Balance")
    fun deleteAll()

    @Query("UPDATE Balance SET  currentBalance = currentBalance+:value WHERE coinBalanceId = :coinId")
    fun updateBalance(coinId: Long, value: Double)

    @Transaction
    @Query("SELECT * FROM Balance")
    fun getBalanceAndCoin(): List<Balance>



}
