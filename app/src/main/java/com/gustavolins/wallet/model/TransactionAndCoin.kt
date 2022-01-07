package com.gustavolins.wallet.model

import androidx.room.Embedded
import androidx.room.Relation

data class TransactionAndCoin(
    @Embedded val transaction: Transaction,

    @Relation(
        parentColumn = "fromCoinId",
        entityColumn = "coinId"
    )
    val fromCoin: Coin,
    @Relation(
        parentColumn = "toCoinId",
        entityColumn = "coinId"
    )
    var toCoin: Coin?

)