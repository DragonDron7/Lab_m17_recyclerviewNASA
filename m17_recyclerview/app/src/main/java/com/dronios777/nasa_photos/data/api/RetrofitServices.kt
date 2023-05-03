package com.dronios777.nasa_photos.data.api

import com.dronios777.nasa_photos.data.ModelPhotoMars
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.http.GET
import retrofit2.http.Query


private const val BASE_URL = "https://api.nasa.gov"
private const val API_KEY ="8ezmcekNcNdSyEQVnr6BJAqJZ3OZ8N41ku5eys6M"

object RetrofitInstance {

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // создание retrofit сервиса
    val searchApi: NasaApi by lazy {
        retrofit.create(NasaApi::class.java)
    }

    interface NasaApi {
        //загрузка фото, сделанных ровером Curiosity
        @GET("/mars-photos/api/v1/rovers/curiosity/photos")

        suspend fun getPhoto(
            @Query("earth_date") earth_date: String,
            @Query("api_key") api_key: String = API_KEY
        ): ModelPhotoMars
    }


}