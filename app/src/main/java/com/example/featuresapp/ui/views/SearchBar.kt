package com.example.featuresapp.ui.views

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.featuresapp.R
import com.example.featuresapp.ui.theme.popBlack
import kotlinx.coroutines.flow.MutableStateFlow


@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    searchFlow: MutableStateFlow<String>,
) {

    var currentSearchValue  by remember { mutableStateOf("") }

    LaunchedEffect(key1 = currentSearchValue) {
        searchFlow.emit(currentSearchValue)
    }

    Column(
        modifier
            .padding(horizontal = 16.dp)
            .border(width = 1.dp, color = colorResource(id = R.color.gray_200), shape =  RoundedCornerShape(8.dp))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_search_magnifier),
                contentDescription = "search icon",
                modifier = Modifier.size(16.dp),
            )
            Spacer(modifier = Modifier.width(12.dp))
            // SearchTextField()
            TextField(
                value = currentSearchValue,
                modifier = Modifier.weight(1f, true),
                maxLines = 1,
                textStyle = TextStyle(
                    fontSize = 16.sp,
                    color = colorResource(id =R.color.gray_400 )
                ),
                placeholder = {
                    Text(text = "Search movies", color = colorResource(id =R.color.gray_400 ))
                },
                onValueChange = {
                    currentSearchValue = it

                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                ),
            )

        }
    }

}

@Preview(showBackground = true)
@Composable
private fun SearchBarPreview() {
    SearchBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding( vertical = 20.dp),
        searchFlow = MutableStateFlow(""),
    )
}
