package jdroidcoder.ua.networkprovider

import retrofit2.http.GET
import rx.Observable

interface Api {
    @GET("lists")
    fun getLists(): Observable<Object>
}