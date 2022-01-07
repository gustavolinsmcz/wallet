package com.gustavolins.wallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Balance(

    @PrimaryKey(autoGenerate = true)
    val balanceId: Long?,

    val accountBalanceId: Long,

    val coinBalanceId: Long,

    val currentBalance: Double

)
