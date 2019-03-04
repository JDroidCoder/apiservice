package jdroidcoder.ua.apiservice.initializer

import android.webkit.URLUtil
import jdroidcoder.ua.apiservice.exception.InitException
import jdroidcoder.ua.apiservice.network.RetrofitConfig
import jdroidcoder.ua.apiservice.util.GlobalData
import retrofit2.Retrofit

object ApiServiceInitializer {

    @Throws(InitException::class)
    fun init(baseUrl: String? = null): Retrofit? {
        if (baseUrl == null || baseUrl?.isNullOrEmpty() == true) {
            throw InitException("Url must be not null or empty")
        }
        if (!URLUtil.isValidUrl(baseUrl)) {
            throw InitException("Invalid Url")
        }
        GlobalData.baseUrl = baseUrl
        return RetrofitConfig.adapter
    }

    private fun setToken(token:String){
        GlobalData.token = token
    }
}
