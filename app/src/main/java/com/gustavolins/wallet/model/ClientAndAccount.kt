package com.gustavolins.wallet.model

import androidx.room.Embedded
import androidx.room.Relation

data class ClientAndAccount(
    @Embedded val client: Client,
    @Relation(
        parentColumn = "clientId",
        entityColumn = "clientAccountId"
    )
    val account: Account

)