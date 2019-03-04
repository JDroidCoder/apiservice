package jdroidcoder.ua.networkprovider

import com.google.gson.annotations.SerializedName
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST
import rx.Observable

interface Api{
    @POST("registration")
    @FormUrlEncoded
    fun register(@Field("email") email:String,
                 @Field("password") password:String):Observable<Token>
}


data class Token(@SerializedName("access_token")var token: String?)