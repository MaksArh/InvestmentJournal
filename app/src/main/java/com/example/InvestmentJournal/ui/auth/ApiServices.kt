package com.example.InvestmentJournal.ui.auth

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Date

interface ApiService {

    @POST("users/login")
    fun login(@Body userData: User): Call<UserResponse>

    @POST("users/register")
    fun register(@Body userData: User): Call<UserResponse>

    @GET("market/check/{ticker}")
    fun checkTicker(@Path("ticker") ticker: String): Boolean

    @GET("market")
    fun getMarketPapers(): Collection<MarketPaper>

    @GET("portfolio/list")
    fun getPortfolioList(@Query("portfolioId") portfolioId: Int, @Query("userId") userId: Int): List<Paper>

    @POST("portfolio/list")
    fun addPaperToPortfolio(@Body portfolioId: Int, userId: Int, paper: Paper): Call<Void>

    @GET("portfolio/{userId}")
    fun getUserPortfolios(@Path("userId") userId: Int): List<Portfolio>

    @POST("portfolio/{userId}")
    fun createPortfolio(@Path("userId") userId: Int, @Body portfolio: Portfolio): Call<Void>
}

data class User(
    val name: String,
    val password: String
)

data class UserResponse(
    val id: Int
)


data class Portfolio(
    var id: Int,
    var name: String,
    var startPrice: Float,
    var currPrice: Float
)

data class Paper(
    var id: Int,
    var name: String,
    var startPrice: Float,
    var currPrice: Float,
    var buyTime: Date,
    var portfolioId: Int,
    var amount: Int
)

data class MarketPaper(
    var id: Int,
    var name: String,
    var startPrice: Float,
    var currPrice: Float,
    var buyTime: Date
)

