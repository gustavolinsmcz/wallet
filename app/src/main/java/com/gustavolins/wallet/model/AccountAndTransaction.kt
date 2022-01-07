package com.gustavolins.wallet.model

import androidx.room.Embedded
import androidx.room.Relation

data class AccountAndTransaction(
    @Embedded val account: Account,
    @Relation(
        parentColumn = "accountId",
        entityColumn = "accountTransactionId"
    )
    val transactions: List<Transaction>

)
