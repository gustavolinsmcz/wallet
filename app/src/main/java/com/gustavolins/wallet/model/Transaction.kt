package com.gustavolins.wallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val transactionId: Long?,

    val accountTransactionId : Long,

    val typeTransaction : Long,

    var value : Double?,

    val fromCoinId : Long?,

    var toCoinId : Long?,

    var valueTo : Double?,
)