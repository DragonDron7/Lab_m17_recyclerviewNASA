package com.dronios777.nasa_photos.data

data class ModelPhotoMars(
    val photos: List<FotoModel>
)

data class FotoModel(
    val id: Int,
    val sol: Int,
    val camera: CameraModel,
    val img_src: String,
    val earth_date:String,
    val rover:RoverModel
)

data class CameraModel(
    val id: Int,
    val name: String,
    val rover_id: Int,
    val full_name: String
)

data class RoverModel(
    val id:Int,
    val name: String,
    val landing_date:String,
    val launch_date:String,
    val status:String
)





