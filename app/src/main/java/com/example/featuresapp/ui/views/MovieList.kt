package com.example.featuresapp.ui.views

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Vertices
import androidx.compose.ui.unit.dp
import com.example.featuresapp.network.model.MovieModel


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun    SharedTransitionScope.MovieVerticalList(
    modifier: Modifier = Modifier,
    list: List<MovieModel>,
    animatedVisibilityScope: AnimatedVisibilityScope,
    onClick:(MovieModel) ->Unit,
) {
    val state = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier.padding(horizontal = 16.dp),

        state = state
    ) {
        itemsIndexed(list) { _, item ->
            this@MovieVerticalList.VerticalItem(
                movieModel = item, modifier = Modifier
                    .padding(top = 17.dp).clickable {
                        onClick(item)
                    } .sharedElement(state = rememberSharedContentState(
                        key = "image-${item.getPosterUrl()}"
                    ), animatedVisibilityScope = animatedVisibilityScope)
                ,
                animatedVisibilityScope = animatedVisibilityScope


            )
        }
    }

}