package com.appcenter.homeexercise.models

data class TrendingState(
    val id: Int,
    val repoName: String?,
    val userName: String?,
    val language: String?,
    val image: String?,
    val description: String?,
    val starCount: Int = 0,
    val page: Int = 0,
    )

