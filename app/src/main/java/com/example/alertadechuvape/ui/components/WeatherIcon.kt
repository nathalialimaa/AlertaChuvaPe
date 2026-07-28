package com.example.alertadechuvape.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImage
import com.example.alertadechuvape.R

@Composable
fun WeatherIcon(

    url: String,

    modifier: Modifier = Modifier

) {

    if (url.isBlank()) {

        Image(

            painter = painterResource(R.drawable.loading),

            contentDescription = null,

            modifier = modifier

        )

    } else {

        AsyncImage(

            model = url,

            contentDescription = null,

            modifier = modifier,

            placeholder = painterResource(R.drawable.loading),

            error = painterResource(R.drawable.loading)

        )

    }

}