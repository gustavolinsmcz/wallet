package com.gustavolins.wallet.database.dao

import androidx.room.*
import com.gustavolins.wallet.model.Account
import com.gustavolins.wallet.model.AccountAndBalance
import com.gustavolins.wallet.model.Client
import com.gustavolins.wallet.model.ClientAndAccount

@Dao
interface AccountDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(account: Account?): Long

    @Query("SELECT DISTINCT * FROM Account")
    fun getAll(): List<Account?>

    @Query("SELECT DISTINCT * FROM Account LIMIT 0,1")
    fun getAccount(): Account

    @Query("DELETE FROM Account")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM Account LIMIT 0,1")
    fun getAccountAndBalance():  AccountAndBalance

}
