package com.gustavolins.wallet.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
class Coin(

    @PrimaryKey(autoGenerate = true)
    val coinId: Long?,

    val nameCoin: String,

    var currentQuote: Double



): Serializable
{
    override fun toString(): String = nameCoin
}