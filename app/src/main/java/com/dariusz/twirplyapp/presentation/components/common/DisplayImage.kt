package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import coil.annotation.ExperimentalCoilApi
import coil.compose.ImagePainter
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter

@ExperimentalCoilApi
@Composable
fun DisplayImage(
    url: String,
    modifier: Modifier,
    description: String? = null,
) {
    val painter = rememberImagePainter(
        data = url,
        builder = {
            crossfade(true)
            allowHardware(false)
        },
        imageLoader = LocalImageLoader.current,
    )

    Box {

        Image(
            painter = painter,
            contentDescription = description,
            modifier = modifier
        )

        when (painter.state) {
            is ImagePainter.State.Loading -> {
                CircularProgressIndicator(Modifier.align(Alignment.Center))
            }
            is ImagePainter.State.Error -> {
                Text("Error loading image")
            }
            else -> {
                //do nothing
            }
        }
    }
}