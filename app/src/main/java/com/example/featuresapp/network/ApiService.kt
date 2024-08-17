package com.example.featuresapp.network

import com.example.featuresapp.network.model.MoviesModel
import org.intellij.lang.annotations.Language
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("{time_window}")
    suspend fun getMovies(
        @Path("time_window") timeWindow: String = "week",
      @Query("language")  language: String = "en-US",
    ):MoviesModel
}