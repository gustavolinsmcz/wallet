package com.gustavolins.wallet.database.dao

import androidx.room.*
import com.gustavolins.wallet.model.Coin
import com.gustavolins.wallet.model.Operation

@Dao
interface OperationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(operation: Operation): Long


    @Query("SELECT DISTINCT * FROM Operation")
    fun getAll(): List<Operation>

}
