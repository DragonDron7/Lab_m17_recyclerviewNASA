package com.dronios777.nasa_photos.data

import com.dronios777.nasa_photos.data.api.RetrofitInstance
import retrofit2.Call
import retrofit2.Response


class Repository {

   suspend  fun getPhoto( earth_date:String): ModelPhotoMars {

       return RetrofitInstance.searchApi.getPhoto(earth_date )
    }
}