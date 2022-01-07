package com.gustavolins.wallet.model

import androidx.room.Embedded
import androidx.room.Relation
import java.io.Serializable

data class AccountAndBalance(
    @Embedded val account: Account,

    @Relation(entity = Balance::class, parentColumn = "accountId", entityColumn = "accountBalanceId"  )
    val balance: List<BalanceAndCoin>,

    @Relation(parentColumn = "coinDefaultId", entityColumn = "coinId"  )
    val coinDefault: Coin
) : Serializable
