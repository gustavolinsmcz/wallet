package com.gustavolins.wallet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity
data class Account(
    @PrimaryKey
    val accountId: Long,

    val number: String,

    val clientAccountId : Long,

    val coinDefaultId: Long

)