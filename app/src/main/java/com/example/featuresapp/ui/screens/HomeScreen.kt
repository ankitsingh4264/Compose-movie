package com.example.featuresapp.ui.screens

import android.provider.Settings.Global.getString
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.featuresapp.R
import com.example.featuresapp.data.Result
import com.example.featuresapp.ui.views.MovieVerticalList
import com.example.featuresapp.ui.views.SearchBar
import com.example.featuresapp.viewmodel.SearchViewModel
import kotlinx.coroutines.flow.MutableStateFlow


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.HomeScreen(
    modifier: Modifier = Modifier,
    searchFlow: MutableStateFlow<String>,
    viewModel: SearchViewModel,
    animatedVisibilityScope: AnimatedVisibilityScope
) {
    val navController = appNavController.current
    Column(
        modifier
    ) {

        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            searchFlow,
        )

        when (val movieList = viewModel.movieListState.value) {
            is Result.Success -> {
                MovieVerticalList(
                    list = movieList.data,
                    modifier = Modifier.padding(top = 13.dp),
                    animatedVisibilityScope = animatedVisibilityScope
                ) {
                    viewModel.selectedMovieForDetails = it
                    navController.navigate(Screens.DETAILS.value)
                }
            }

            is Result.Error -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_error),
                            contentDescription = "error"
                        )
                        Text(
                            movieList.msg ?: "",
                            modifier = Modifier.padding(top = 15.dp)
                        )
                    }

                }
            }

            is Result.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(Modifier.align(Alignment.Center))
                }
            }

            else -> {
                //no-op
            }
        }

    }
}