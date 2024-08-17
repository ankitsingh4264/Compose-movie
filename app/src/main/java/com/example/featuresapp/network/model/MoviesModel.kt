package com.example.featuresapp.network.model

import com.google.gson.annotations.SerializedName

data class MoviesModel(
    @SerializedName("results") val result: List<MovieModel>?,
)
