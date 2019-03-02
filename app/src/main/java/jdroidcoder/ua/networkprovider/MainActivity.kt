package jdroidcoder.ua.networkprovider

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import jdroidcoder.ua.apiservice.network.RetrofitSubscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        ApiServiceInitializer
//        val x = Temp.
        ApiService.api?.getLists()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.unsubscribeOn(Schedulers.io())
            ?.subscribe(object : RetrofitSubscriber<Object>() {
                override fun onNext(response: Object) {
                    println("dsa")
                }
            })
//        println("dsa")
    }
}
