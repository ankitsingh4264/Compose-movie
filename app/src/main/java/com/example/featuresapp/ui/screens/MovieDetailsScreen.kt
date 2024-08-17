package com.example.featuresapp.ui.screens

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.featuresapp.R
import com.example.featuresapp.network.model.MovieModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.MovieDetailScreen(
    modifier: Modifier = Modifier,
    movieModel: MovieModel?,
    animatedVisibilityScope: AnimatedContentScope
) {
    val navController = appNavController.current
    Column(
        modifier
            .background(color = Color.White)
            .fillMaxSize()
            .padding(horizontal = 16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_back),
            contentDescription = "back",
            modifier = Modifier
                .padding(top = 30.dp)
                .size(25.dp)
                .clickable {
                    navController.popBackStack()
                }
        )
        Column(modifier = Modifier.sharedElement(
            rememberSharedContentState(key = "image-${movieModel?.getPosterUrl()}"),
            animatedVisibilityScope,
        )) {

            AsyncImage(
                model = movieModel?.getPosterUrl(),
                contentDescription = "poster",
                imageLoader = ImageLoader(context = LocalContext.current),
                modifier = Modifier
                    .padding(top = 24.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .fillMaxWidth()
                    .aspectRatio(1f),
                contentScale = ContentScale.Crop
            )
            Text(
                text = movieModel?.title ?: "", style = TextStyle(
                    fontSize = 24.sp,
                    color = Color.Black,
                    lineHeight = 32.sp
                ), modifier = Modifier.padding(top = 24.dp)
            )
        }

        Text(text =movieModel?.overview?:"", style = TextStyle(
            fontSize = 16.sp,
            color = Color.Black,
            lineHeight = 24.sp
        ), modifier = Modifier.padding(top = 24.dp))

    }
}