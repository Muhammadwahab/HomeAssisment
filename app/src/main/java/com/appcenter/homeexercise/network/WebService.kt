package com.appcenter.homeexercise.network

import com.appcenter.homeexercise.network.NetworkUtils.SEARCH_TRENDING_REPOS
import com.appcenter.homeexercise.network.responsemodel.TrendingResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebService {
    @GET(SEARCH_TRENDING_REPOS)
    suspend fun getProfile(@Query("q") queryCode: String?,@Query("page") page: Int,@Query("per_page") per_page: Int): TrendingResponse
}