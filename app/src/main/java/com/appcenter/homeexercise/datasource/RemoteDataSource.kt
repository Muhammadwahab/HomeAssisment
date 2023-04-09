package com.appcenter.homeexercise.datasource

import com.appcenter.homeexercise.Constant
import com.appcenter.homeexercise.network.Result
import com.appcenter.homeexercise.network.WebService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val webService: WebService, private val coroutineScope: CoroutineScope=CoroutineScope(Dispatchers.IO)) {

    suspend fun getTradingList(page: Int, searchRepo: String): Result<*> {

        return withContext(coroutineScope.coroutineContext) {
            try {
                val data = webService.getProfile(searchRepo,page,Constant.PER_PAGE)
                Result.Success(data)
            } catch (exception: Exception) {
                Result.Error<String>(exception.toString())
            }
        }
    }
}