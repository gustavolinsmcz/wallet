package com.gustavolins.wallet.model

import com.google.gson.annotations.SerializedName


data class Usdbrl (

 @SerializedName("code") var code : String? ,
 @SerializedName("codein") var codein  : String? ,
 @SerializedName("name") var name : String? ,
 @SerializedName("high") var high : String? ,
 @SerializedName("low" ) var low: String? ,
 @SerializedName("varBid") var varBid: String? ,
 @SerializedName("pctChange") var pctChange  : String? ,
 @SerializedName("bid" ) var bid: String? ,
 @SerializedName("ask" ) var ask: String? ,
 @SerializedName("timestamp") var timestamp  : String? ,
 @SerializedName("create_date" ) var createDate : String?

)