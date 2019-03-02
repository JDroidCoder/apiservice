package jdroidcoder.ua.networkprovider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jdroidcoder.ua.apiservice.initializer.ApiService

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val x = ApiService.provider
        println("dsa")
    }
}
