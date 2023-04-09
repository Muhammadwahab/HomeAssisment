package com.appcenter.homeexercise.datasource.localDataSource.database

import javax.inject.Inject

class DatabaseSource @Inject constructor(private val trendingDao: TrendingDao) {


    suspend fun getTrendingRepos(page: Int):List<Trending> {
       return trendingDao.getTrendingRepo(page)
    }

    suspend fun storeTrendingInCache(listOfTrending: List<Trending>) {
        trendingDao.insertAll(listOfTrending)
    }
}