package com.gustavolins.wallet.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation
import java.io.Serializable

data class BalanceAndCoin(
    @Embedded val balance: Balance,
    @Relation(
        parentColumn = "coinBalanceId",
        entityColumn = "coinId"
    )
    val coin: Coin
) : Serializable