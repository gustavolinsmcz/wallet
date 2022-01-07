package com.gustavolins.wallet.database


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gustavolins.wallet.database.dao.*
import com.gustavolins.wallet.model.*

private const val DATABASE_NAME = "wallesssst1s.db"

@Database(
    entities = [Client::class, Account::class , Coin::class, Balance::class, Operation::class, Transaction::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getClientDao(): ClientDao
    abstract fun getAccountDao(): AccountDao
    abstract fun getCoinDao(): CoinDao
    abstract fun getBalanceDao(): BalanceDao
    abstract fun getOperationDao(): OperationDao
    abstract fun getTransaction(): TransactionDao

    companion object {

        private lateinit var db: AppDatabase

        fun getInstance(context: Context): AppDatabase {
            return if (::db.isInitialized) {
                db
            } else {
                Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    DATABASE_NAME
                ).build()
            }
        }

    }

}