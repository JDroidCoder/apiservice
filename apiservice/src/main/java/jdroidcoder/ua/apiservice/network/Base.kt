package jdroidcoder.ua.apiservice.network

import com.google.gson.annotations.SerializedName

class Base(@SerializedName("status") var status:Int,
           @SerializedName("message")var message:String)