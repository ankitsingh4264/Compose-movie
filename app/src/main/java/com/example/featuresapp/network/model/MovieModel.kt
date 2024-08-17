package com.example.featuresapp.network.model

import android.media.Rating
import android.os.IBinder
import com.google.gson.annotations.SerializedName

const val baseImageUrl = "https://image.tmdb.org/t/p/w500/"

data class MovieModel(
   @SerializedName("title") val title: String,
   @SerializedName("overview") val overview: String,
   @SerializedName("poster_path")  val poster: String,

){
   fun getPosterUrl() = baseImageUrl + poster
}


