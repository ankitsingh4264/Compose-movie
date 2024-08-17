package com.example.featuresapp.ui.views

import android.util.Log
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.AsyncImage
import com.example.featuresapp.network.model.MovieModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.VerticalItem(modifier: Modifier = Modifier, movieModel: MovieModel,animatedVisibilityScope: AnimatedVisibilityScope) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(bottom = 10.dp),
        horizontalAlignment = Alignment.Start,
    ) {

            AsyncImage(
                model = movieModel.getPosterUrl(),
                contentDescription = "poster",
                imageLoader = ImageLoader(context = LocalContext.current),
                modifier = Modifier
                    .clip(
                        shape = RoundedCornerShape(10.dp)
                    )
                    .size(150.dp)

                ,
                contentScale = ContentScale.Crop
            )

        Text(
            modifier = Modifier.padding(top = 12.dp),
            text = movieModel.title, style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                lineHeight = 24.sp
            ),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )


    }
}