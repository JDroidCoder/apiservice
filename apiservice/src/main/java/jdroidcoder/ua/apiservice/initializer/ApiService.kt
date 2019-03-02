package jdroidcoder.ua.apiservice.initializer

import android.webkit.URLUtil
import jdroidcoder.ua.apiservice.exception.InitException
import jdroidcoder.ua.apiservice.network.ApiProvider
import jdroidcoder.ua.apiservice.network.RetrofitConfig
import jdroidcoder.ua.apiservice.util.GlobalData

object ApiService {
    var provider: ApiProvider? = null

    @Throws(InitException::class)
    fun init(baseUrl: String? = null, apiService: ApiProvider) {
        if (baseUrl == null || baseUrl?.isNullOrEmpty() == true) {
            throw InitException("Url must be not null or empty")
        }
        if (!URLUtil.isValidUrl(baseUrl)) {
            throw InitException("Invalid Url")
        }
        if (apiService == null) {
            throw InitException("ApiProvider must be not null")
        }
        GlobalData.baseUrl = baseUrl
//       val apiInstance=  Proxy.newProxyInstance(apiService.classLoader,arrayOf(apiService)) { proxy, method, args ->
//            val ret = method?.invoke(GlobalData.apiServiceImpl, *(args ?: arrayOfNulls<Any>(0)))
//            ret
//        } as ApiProvider
        val list = mutableListOf<Any>()
        apiService::class.java.declaredFields.forEach {
            it.isAccessible = true
            list.add(it)
            it.isAccessible = false
        }

        val constructor = ApiProvider::class.java.declaredConstructors[0]
       val x =constructor.newInstance(*list.toTypedArray()) as ApiProvider
        GlobalData.apiServiceImpl = apiService
        provider = RetrofitConfig.adapter as ApiProvider
    }
}