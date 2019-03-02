package jdroidcoder.ua.apiservice.util

import jdroidcoder.ua.apiservice.network.ApiProvider

internal object GlobalData {
    var baseUrl: String = ""
    var apiServiceImpl: ApiProvider? = null
}