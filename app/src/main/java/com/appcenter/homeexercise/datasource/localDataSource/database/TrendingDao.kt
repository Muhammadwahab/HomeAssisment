package com.appcenter.homeexercise.datasource.localDataSource.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TrendingDao {
    @Query("SELECT * FROM Trending WHERE page=:page")
    suspend fun getTrendingRepo(page:Int): List<Trending>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(trendingState: List<Trending>)

}