package jdroidcoder.ua.networkprovider

import android.app.Application
import jdroidcoder.ua.apiservice.initializer.ApiService

class Test:Application(){
    override fun onCreate() {
        super.onCreate()
        ApiService.init("http://54.144.35.144/api/",Api::class.java.newInstance())

    }
}