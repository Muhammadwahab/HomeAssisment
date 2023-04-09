package com.appcenter.homeexercise.di

import android.content.Context
import com.appcenter.homeexercise.Constant.TIMEOUT
import com.appcenter.homeexercise.R
import com.appcenter.homeexercise.network.APIInterceptor
import com.appcenter.homeexercise.network.WebService
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {



  @Provides
  @Singleton
  fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
  }

  @Provides
  @Singleton
  fun provideApiInterceptor(): APIInterceptor {
    return APIInterceptor()
  }

  @Provides
  @Singleton
  fun provideOkhttpClient(httpLoggingInterceptor:HttpLoggingInterceptor,apiInterceptor: APIInterceptor):  OkHttpClient.Builder {
    val okHttpBuilder =
      OkHttpClient.Builder().connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)

    okHttpBuilder.addInterceptor(httpLoggingInterceptor)
    okHttpBuilder.addInterceptor(apiInterceptor)
    return okHttpBuilder
  }


  @Provides
  @Singleton
  fun provideWebService(@ApplicationContext appContext: Context,builder: OkHttpClient.Builder):  WebService {
    val retrofit = Retrofit.Builder().baseUrl(appContext.getString(R.string.BASE_URL))
      .client(builder.build())
      .addConverterFactory(GsonConverterFactory.create(Gson())).build()
    return retrofit.create(WebService::class.java)
  }
}