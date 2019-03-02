package jdroidcoder.ua.apiservice.network

import java.io.IOException

class ApiException(var status: Int?, override var message: String?) : IOException()