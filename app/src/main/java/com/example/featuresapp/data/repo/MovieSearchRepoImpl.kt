package com.example.featuresapp.data.repo

import android.content.Context
import android.util.Log
import com.example.featuresapp.data.Result
import com.example.featuresapp.network.ApiService
import com.example.featuresapp.network.model.MovieModel
import com.example.featuresapp.network.model.MoviesModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject


class MovieSearchRepoImpl @Inject constructor (
    private val apiService: ApiService,
    @ApplicationContext val context: Context

): MovieSearchRepoContract{

    override suspend fun getMoviesList(): Flow<Result<List<MovieModel>?>> {
        return flow {
              emit(Result.Loading)
              try {
                  val response = apiService.getMovies()
                  Log.d("ankit", "getMoviesList: ${response?.result}")
                  val currentList = response?.result
                  emit(Result.Success(currentList))

              } catch (e: Exception) {
                  Log.d("ankit", "getMoviesList: ${e.message}")

                  emit(Result.Error(e.message))
              }
       }
    }
    fun loadJSONFromAsset( fileName: String): MoviesModel? {
        return try {
            val inputStream = context.assets.open(fileName)
            val size = inputStream.available()
            val buffer = ByteArray(size)
            inputStream.read(buffer)
            inputStream.close()
            parseMoviesJson( String(buffer, Charsets.UTF_8))
        } catch (ex: IOException) {

            ex.printStackTrace()
            null
        }
    }

    fun parseMoviesJson(json: String): MoviesModel {
        val gson = Gson()
        val type = object : TypeToken<MoviesModel>() {}.type
        return gson.fromJson(json, type)
    }

}
