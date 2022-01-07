package com.gustavolins.wallet.repository

import com.gustavolins.wallet.retrofit.RetrofitInstance
import com.gustavolins.wallet.ultils.Constants
import javax.inject.Inject


class AppRepository @Inject constructor() {

    suspend fun getBitcoinQuote() = RetrofitInstance.api.getBitcoinQuote(Constants.URL_QUOTE_BITCOIN)

    suspend fun getBritasQuote() = RetrofitInstance.api.getBritasQuote(Constants.URL_QUOTE_BRITAS)

}