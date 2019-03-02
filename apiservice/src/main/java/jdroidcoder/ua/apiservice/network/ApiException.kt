package jdroidcoder.ua.apiservice.network

import java.io.IOException

class ApiException(var status: String?, override var message: String?) : IOException()