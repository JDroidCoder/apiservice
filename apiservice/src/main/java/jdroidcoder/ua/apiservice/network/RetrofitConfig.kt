package jdroidcoder.ua.apiservice.network

import jdroidcoder.ua.apiservice.util.GlobalData
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by jdroidcoder on 16.02.2018.
 */
internal object RetrofitConfig {

    val adapter: ApiProvider?
        get() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
//                    .addInterceptor(AuthInterceptor())
                    .build()

            val retrofit = Retrofit.Builder()
                    .baseUrl(GlobalData.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build()

            return retrofit.create(GlobalData.apiServiceImpl!!::class.java)
        }

//    private class AuthInterceptor : Interceptor {
//
//        @Throws(IOException::class)
//        override fun intercept(chain: Interceptor.Chain): Response {
////            val token = GlobalData.token?.token
////            val original = chain.request()
////            return if (!TextUtils.isEmpty(token)) {
////                val requestBuilder = original.newBuilder()
////                        .header("Language", GlobalData.language)
////                        .header("Authorization", "Basic " + HttpRequest.Base64.encode("$token:"))
////                        .method(original.method(), original.body())
////
////                chain.proceed(requestBuilder.build())
////            } else {
////                chain.proceed(original)
////            }
//        }
//    }
}