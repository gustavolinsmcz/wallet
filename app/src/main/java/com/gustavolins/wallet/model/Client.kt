package com.gustavolins.wallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Client(

    @PrimaryKey(autoGenerate = true)
    val clientId: Long,

    val nameClient: String

)
