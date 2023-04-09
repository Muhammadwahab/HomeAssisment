package com.appcenter.homeexercise.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.appcenter.homeexercise.datasource.localDataSource.database.Trending
import com.appcenter.homeexercise.models.TrendingState
import com.appcenter.homeexercise.network.Result
import com.appcenter.homeexercise.network.responsemodel.TrendingResponse
import com.appcenter.homeexercise.repositry.TrendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val trendingRepository: TrendingRepository
) : ViewModel() {
    fun trendingRepos(page: Int, isRefresh: Boolean, searchRepo: String):Flow<Result<*>> = flow {

        emit(Result.Loading<String>(null))

        trendingRepository.getTrendingRepos(page,searchRepo).apply {
            when(this){

                is Result.Success -> {
                    if (this.data is TrendingResponse){ // for api response
                        Log.e(HomeViewModel::class.simpleName,"Success TrendingResponse " +this.data)
                        val listOfTradingStates=this.data.items?.map {
                            TrendingState(
                                it.id,
                                it.name,
                                it.owner.login,
                                it.language,
                                it.owner.avatarUrl,
                                it.description,
                                it.stargazersCount,
                                page
                            )
                        }

                        if (listOfTradingStates?.isEmpty() == true){
                            emit(Result.Error<String>("No repositry found"))
                        }else{
                            emit(Result.Success(listOfTradingStates))

                        }

                    }
                }
                else -> {
                    emit(Result.Error<String>("Some thing went wrong"))
                    Log.e(HomeViewModel::class.simpleName,"error ${this}")

                }
            }
        }
    }
}