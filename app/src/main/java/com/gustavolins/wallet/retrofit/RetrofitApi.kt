package com.gustavolins.buscacep

import com.gustavolins.wallet.model.BritasQuote
import com.gustavolins.wallet.model.Ticker
import retrofit2.Response
import retrofit2.http.*

interface RetrofitApi {

    @GET()
    suspend fun getBitcoinQuote(@Url url : String): Response<Ticker>

    @GET()
    suspend fun getBritasQuote(@Url url : String): Response<BritasQuote>

}