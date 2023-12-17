package com.example.finwise.ui.news

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import okhttp3.OkHttpClient
import okhttp3.Request

data class GlobalQuote(
    @SerializedName("01. symbol") val symbol: String="",
    @SerializedName("02. open") val open: String="",
    @SerializedName("03. high") val high: String="",
    @SerializedName("04. low") val low: String="",
    @SerializedName("05. price") val price: String="",
    @SerializedName("06. volume") val volume: String="",
    @SerializedName("07. latest trading day") val latestTradingDay: String="",
    @SerializedName("08. previous close") val previousClose: String="",
    @SerializedName("09. change") val change: String="",
    @SerializedName("10. change percent") val changePercent: String=""
)

data class ApiResponse(@SerializedName("Global Quote") val globalQuote: GlobalQuote)

fun getQuote(name: String): ApiResponse  {
    val client = OkHttpClient()
    val request = Request.Builder().url("https://www.alphavantage.co/query?function=GLOBAL_QUOTE&symbol=$name&apikey=88X174VU2KDLY7QL").build()
    val response = client.newCall(request).execute()
    val jsonResponse = response.body?.string() ?: ""

    return Gson().fromJson(jsonResponse, ApiResponse::class.java)
}
