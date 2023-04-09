package com.appcenter.homeexercise.repositry

import com.appcenter.homeexercise.datasource.localDataSource.database.DatabaseSource
import com.appcenter.homeexercise.datasource.localDataSource.database.Trending
import com.appcenter.homeexercise.datasource.RemoteDataSource
import com.appcenter.homeexercise.network.Result
import com.appcenter.homeexercise.network.responsemodel.TrendingResponse
import javax.inject.Inject

class TrendingRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val dataSource: DatabaseSource
) {

    suspend fun getTrendingRepos(page: Int, searchRepo: String): Result<*> {

        remoteDataSource.getTradingList(page,searchRepo).apply {

            return when (this) {
                is Result.Success -> {
                    val trendingResponse = this.data as TrendingResponse
                    val listOfTrending = trendingResponse.items?.map {
                        Trending(
                            it.id,
                            it.owner.avatarUrl,
                            it.language,
                            it.owner.login,
                            it.name,
                            it.description,
                            it.stargazersCount,
                            page
                        )
                    }
                    this
                }
                else -> {
                    this
                }
            }
        }

    }
}