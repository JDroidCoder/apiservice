package jdroidcoder.ua.apiservice.network

import android.text.TextUtils
import jdroidcoder.ua.apiservice.util.GlobalData
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

/**
 * Created by jdroidcoder on 16.02.2018.
 */
internal object RetrofitConfig {

    val adapter: Retrofit?
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .addInterceptor(AuthInterceptor())
                    .build()

            return Retrofit.Builder()
                    .baseUrl(GlobalData.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()
        }

    private class AuthInterceptor : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val token = GlobalData.token
            val original = chain.request()
            return if (!TextUtils.isEmpty(token)) {
                val requestBuilder = original.newBuilder()
                        .header(GlobalData.tokenLabel, token)
                        .method(original.method(), original.body())

                chain.proceed(requestBuilder.build())
            } else {
                chain.proceed(original)
            }
        }
    }

}