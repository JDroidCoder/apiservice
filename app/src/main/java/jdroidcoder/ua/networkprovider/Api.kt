package jdroidcoder.ua.networkprovider

import jdroidcoder.ua.apiservice.network.ApiProvider
import retrofit2.http.GET
import rx.Observable

interface Api : ApiProvider {
    @GET("lists")
    fun getLists(): Observable<Object>
}