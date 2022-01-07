package com.gustavolins.wallet.retrofit


import com.gustavolins.buscacep.RetrofitApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInstance {
    companion object {


        private val retrofitInstance by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()
            Retrofit.Builder()
                .baseUrl("https://www.mercadobitcoin.net/api/BTC/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
        }
        val api by lazy {
            retrofitInstance.create(RetrofitApi::class.java)
        }

    }
}