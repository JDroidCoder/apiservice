package jdroidcoder.ua.networkprovider

import android.app.Application
import jdroidcoder.ua.apiservice.initializer.ApiServiceInitializer

class Test : Application() {
    override fun onCreate() {
        super.onCreate()
        ApiService.api = ApiServiceInitializer.init("http://54.144.35.144/api/")?.create(Api::class.java)
//        val x = Temp.ApiServiceInitializer<Api>()
//        x.saveConfigs(x.init("http://54.144.35.144/api/")?.create(Api::class.java))
//        val y  = x.provider
//        println("dsa")
//      val temp = ApiServiceInitializer().init("http://54.144.35.144/api/")
//          ?.create(Api::class.java)
//        ApiServiceInitializer.saveConfigs(temp)
    }

}

object ApiService {
    var api: Api? = null
}