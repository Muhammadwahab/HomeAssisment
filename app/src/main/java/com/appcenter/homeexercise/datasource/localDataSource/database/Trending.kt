package com.appcenter.homeexercise.datasource.localDataSource.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Trending(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "avatarUrl") val avatarUrl: String?,
    @ColumnInfo(name = "language") val language: String?,
    @ColumnInfo(name = "login_name") val userName: String?,
    @ColumnInfo(name = "repoName") val name: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "starCount") val starCount: Int=0,
    @ColumnInfo(name = "page") val page: Int=0,
    )
