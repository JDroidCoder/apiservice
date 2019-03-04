# apiservice

This library based on Retrofit2, and has a purpose easy work with REST API.
Opportunities :
1 - Support RxJava/RxAndroid
2 - Easy treatment standard REST API errors
3 - Easy work with OAuth Token
4 - Easy work with SocketTimeoutException and lost internet connection

You can initialize library in App class or anywhere in app.

Install:
1 - Add jitpack repository in gradle(project level)
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}

2 - Add dependency in gradle(app level)
dependencies {
	 implementation 'com.github.JDroidCoder:apiservice:Tag'
}

3 - enjoy ;)

Initialize library:
ApiServiceInitializer.init("base url").create("you api interface) - will returned Api interface, you can save him in global variable.

Code example:

Response class:
data class Token(@SerializedName("access_token")var token: String?)

API interface 
interface Api{
    @POST("registration")
    @FormUrlEncoded
    fun register(@Field("email") email:String,
                 @Field("password") password:String):Observable<Token>
}

Global object:
object GlobalData{
    var apiService:Api? = null
}

Init library and save in global object:
GlobalData.apiService = ApiServiceInitializer.init("base url")?.create(Api::class.java)

Send request to server with RX and save token in headers for next requests:
GlobalData.apiService?.register("example@gmail.com","123456")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.unsubscribeOn(Schedulers.io())
            ?.subscribe(object : RetrofitSubscriber<Token>() {
                override fun onNext(response: Token) {
                     ApiServiceInitializer.setToken("Authorization", response.token)
                }
            })

Work with API exceptions:
Global.apiService?.register("example@gmail.com","123456")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.unsubscribeOn(Schedulers.io())
            ?.subscribe(object : RetrofitSubscriber<Token>() {
                override fun onNext(response: Token) {
                    response.token
                }

                override fun apiException(apiException: ApiException) {
                    Toast.makeText(context,"${apiException.status} ${apiException.message}")
                }
            })

Work with SocketTimeoutException and lost internet:
Global.apiService?.register("example@gmail.com","123456")
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.unsubscribeOn(Schedulers.io())
            ?.subscribe(object : RetrofitSubscriber<Token>() {
                override fun onNext(response: Token) {
                    response.token
                }

                override fun apiException(apiException: ApiException) {
                    super.apiException(apiException)
                    Toast.makeText(context,"${apiException.status} ${apiException.message}")
                }

                override fun noInternet() {
                   //lost internet connection
                }

                override fun retry() {
                   //SocketTimeoutException
                }
            })

If you have any question ask us here or write to email: jdroidcoder@gmail.com