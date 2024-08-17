package com.example.featuresapp.viewmodel

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.featuresapp.data.Result
import com.example.featuresapp.data.repo.MovieSearchRepoContract
import com.example.featuresapp.network.model.MovieModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel  @Inject constructor(
    private val searchRepo: MovieSearchRepoContract
) :ViewModel() {

    val movieListState = mutableStateOf<Result<List<MovieModel>>>(Result.Loading)
    var currentList = emptyList<MovieModel>()
    var selectedMovieForDetails: MovieModel? = null


    fun getMovies() {

        viewModelScope.launch {


            searchRepo.getMoviesList().collectLatest {
                when (it) {
                    is Result.Success -> {
                        currentList = it.data ?: emptyList()
                        movieListState.value =
                            if (it.data == null) Result.Success(emptyList()) else Result.Success(currentList)
                    }

                    is Result.Error -> {

                        movieListState.value = Result.Error(it.msg)
                    }

                    is Result.Loading -> {

                        movieListState.value = Result.Loading
                    }
                    else -> {
                        //no-op
                    }
                }
            }
        }
    }

   fun searchMovies(search: String) {
        viewModelScope.launch {
            if (search == "") {
                movieListState.value = Result.Success(currentList)
                return@launch
            }
           val searchedList =  currentList.filter {
                it.title.contains(search, ignoreCase = true)
            }
            Log.d("ankit", "searchMovies: ${searchedList.size}")
            movieListState.value = Result.Success(searchedList)
        }
    }

}





