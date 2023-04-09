package com.appcenter.homeexercise.network.responsemodel

import com.google.gson.annotations.SerializedName

data class TrendingResponse(

	@field:SerializedName("items")
	val items: List<ItemsItem>? = null
)

data class Owner(

	@field:SerializedName("avatar_url")
	val avatarUrl: String? = null,

	@field:SerializedName("login")
	val login: String? = null
)

data class ItemsItem(

	@field:SerializedName("owner")
	val owner: Owner,

	@field:SerializedName("stargazers_count")
	val stargazersCount: Int = 0,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("language")
	val language: String? = null,


	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int = 0
)
