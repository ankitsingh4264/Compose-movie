package com.example.featuresapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.featuresapp.ui.screens.HomeScreen
import com.example.featuresapp.ui.screens.MovieDetailScreen
import com.example.featuresapp.ui.screens.Screens
import com.example.featuresapp.ui.screens.LocalAppNavController
import com.example.featuresapp.ui.theme.FeaturesAppTheme

import com.example.featuresapp.viewmodel.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel by viewModels<SearchViewModel>()
    private val searchFlow = MutableStateFlow<String>("")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        viewModel.getMovies()
        setContent {
            FeaturesAppTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AppNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
        collectSearchValues()
        setupStatusBar()
    }


    @OptIn(FlowPreview::class)
    private fun collectSearchValues() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                searchFlow.debounce(200).collectLatest {
                    viewModel.searchMovies(it)
                }
            }

        }

    }

    @OptIn(ExperimentalSharedTransitionApi::class)
    @Composable
    fun AppNavHost(modifier: Modifier = Modifier) {
        val navController = rememberNavController()
        SharedTransitionLayout {

            CompositionLocalProvider(value = LocalAppNavController provides navController) {

                NavHost(
                    navController = navController,
                    startDestination = "home",
                    modifier = modifier
                ) {
                    composable(Screens.HOME.value) {
                        HomeScreen(searchFlow = searchFlow, viewModel = viewModel, animatedVisibilityScope = this@composable)
                    }
                    composable(Screens.DETAILS.value) {
                        MovieDetailScreen(movieModel = viewModel.selectedMovieForDetails, animatedVisibilityScope = this@composable)
                    }

                }

            }
        }
    }
    private fun setupStatusBar() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = true
        window.statusBarColor = getColor(R.color.white) // Replace with your custom color
    }

}






