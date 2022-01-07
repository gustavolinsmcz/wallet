package com.gustavolins.wallet.database.dao

import androidx.room.*
import com.gustavolins.wallet.model.Client
import com.gustavolins.wallet.model.ClientAndAccount

@Dao
interface ClientDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cli: Client?): Long

    @Query("SELECT DISTINCT * FROM Client LIMIT 0,1")
    fun getClient():  Client

    @Query("DELETE FROM Client")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM Client")
    fun getClientAndAccount(): List<ClientAndAccount>
}
