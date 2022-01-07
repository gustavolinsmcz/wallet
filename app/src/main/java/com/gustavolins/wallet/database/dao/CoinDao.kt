package com.gustavolins.wallet.database.dao

import androidx.room.*
import com.gustavolins.wallet.model.Coin

@Dao
interface CoinDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(coin: Coin?): Long

    @Query("SELECT DISTINCT * FROM Coin")
    fun getAll(): List<Coin>

    @Query("SELECT DISTINCT * FROM Coin WHERE coinId != 1")
    fun getCryptocurrencies(): List<Coin>

    @Query("UPDATE Coin SET currentQuote=:quote WHERE coinId = :id")
    fun update(quote: Double?, id: Long?)

}
