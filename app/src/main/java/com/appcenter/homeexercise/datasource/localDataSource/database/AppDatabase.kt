package com.appcenter.homeexercise.datasource.localDataSource.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Trending::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trendingDao(): TrendingDao
}