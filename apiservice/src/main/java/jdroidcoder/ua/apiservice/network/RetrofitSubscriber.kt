package jdroidcoder.ua.apiservice.network

import android.content.Context
import com.google.gson.Gson
import retrofit2.adapter.rxjava.HttpException
import rx.Subscriber
import rx.exceptions.OnErrorFailedException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by jdroidcoder on 16.02.2018.
 */
abstract class RetrofitSubscriber<T> : Subscriber<T>() {
    private var context: Context? = null

    override fun onNext(result: T) {}

    override fun onError(e: Throwable) {
        when (e) {
            is HttpException -> {
                try {

                    val body = e.response().errorBody()
                    val gson = Gson()
                    val adapter = gson.getAdapter(Base::class.java)
                    val errorParser = adapter.fromJson(body?.string())
                    apiException(ApiException(errorParser?.status, errorParser?.message))
                } catch (ex: Exception) {

                }
            }
            is OnErrorFailedException -> e.printStackTrace()
            is SocketTimeoutException -> {
                retry()
            }
            is UnknownHostException -> {
                try {
                    noInternet()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
            else -> {
                onCompleted()
                e.printStackTrace()
            }
        }
    }

    open fun logout(){

    }

    open fun noInternet(){}

    open fun retry(){}

    open fun apiException(apiException: ApiException) {

    }

    override fun onCompleted() {

    }
}