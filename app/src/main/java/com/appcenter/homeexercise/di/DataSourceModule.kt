package com.appcenter.homeexercise.di

import android.content.Context
import androidx.room.Room
import com.appcenter.homeexercise.Constant.DATABASE_NAME
import com.appcenter.homeexercise.datasource.localDataSource.database.AppDatabase
import com.appcenter.homeexercise.datasource.localDataSource.database.DatabaseSource
import com.appcenter.homeexercise.datasource.localDataSource.database.TrendingDao
import com.appcenter.homeexercise.datasource.RemoteDataSource
import com.appcenter.homeexercise.network.WebService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideRemoteDataSource(webService: WebService
    ): RemoteDataSource {
        return RemoteDataSource(webService)
    }


    @Provides
    @Singleton
    fun providerDatabase(@ApplicationContext appContext: Context
    ): AppDatabase {
       return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun providerDatabaseSource(appDatabase: TrendingDao
    ): DatabaseSource {
        return DatabaseSource(appDatabase)
    }

    @Provides
    fun providerTrendingDao(appDatabase: AppDatabase
    ): TrendingDao {
        return appDatabase.trendingDao()
    }
}