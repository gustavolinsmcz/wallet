package com.gustavolins.wallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Operation(

    @PrimaryKey(autoGenerate = true)
    val operationId: Long?,
    val nameOperation: String
)