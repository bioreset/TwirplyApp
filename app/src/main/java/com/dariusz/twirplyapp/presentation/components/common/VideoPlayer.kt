package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.dariusz.twirplyapp.domain.model.Media
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.ui.StyledPlayerView
import kotlinx.coroutines.launch


@Composable
fun PlayVideo(url: String, media: Media) {
    val player = rememberVideoPlayer()
    val playerView = rememberStyledPlayerView(media)
    val mediaItemToShow = getMediaItem(url)
    VideoPlayerContainer(
        player = player,
        playerView = playerView,
        mediaItem = mediaItemToShow
    )
}

@Composable
fun VideoPlayerContainer(
    player: SimpleExoPlayer,
    playerView: StyledPlayerView,
    mediaItem: MediaItem
) {
    var playerInitialized by remember(player) { mutableStateOf(false) }
    val autoPlayStatus = remember(player) { mutableStateOf(false) }
    val videoPosition = remember(player) { mutableStateOf(0L) }

    LaunchedEffect(player, playerInitialized) {
        if (!playerInitialized) {
            player.prepare()
            player.setMediaItem(mediaItem)
            player.playWhenReady = autoPlayStatus.value
            player.seekTo(videoPosition.value)
            playerView.player = player
            playerInitialized = true
        }
    }

    val currentCoroutineScope = rememberCoroutineScope()
    AndroidView(
        { playerView }, modifier = Modifier
            .fillMaxWidth()
            .heightIn(150.dp)
    ) { playerViewX ->
        currentCoroutineScope.launch {
            player.prepare()
            player.setMediaItem(mediaItem)
            player.playWhenReady = autoPlayStatus.value
            player.seekTo(videoPosition.value)
            playerViewX.player = player
        }
    }

}

@Composable
fun rememberStyledPlayerView(media: Media): StyledPlayerView {
    val context = LocalContext.current
    val playerView = remember {
        StyledPlayerView(context)
    }
    playerView.showController()
    return playerView
}

@Composable
fun rememberVideoPlayer(): SimpleExoPlayer {
    val context = LocalContext.current
    val player = remember {
        SimpleExoPlayer.Builder(context).build()
    }
    val lifecycleObserver = rememberVideoPlayerLifecycleObserver(player)
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    DisposableEffect(lifecycle) {
        lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycle.removeObserver(lifecycleObserver)
            player.release()
        }
    }
    return player
}

@Composable
fun rememberVideoPlayerLifecycleObserver(player: SimpleExoPlayer) =
    remember(player) {
        LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> player.prepare()
                Lifecycle.Event.ON_START -> player.playWhenReady
                Lifecycle.Event.ON_RESUME -> player.playWhenReady
                Lifecycle.Event.ON_PAUSE -> player.pause()
                Lifecycle.Event.ON_STOP -> {
                    player.stop(false)
                    !player.playWhenReady
                }
                Lifecycle.Event.ON_DESTROY -> {
                    player.stop(true)
                    player.release()
                }
                else -> throw IllegalStateException()
            }
        }
    }

@Composable
fun getMediaItem(
    url: String
): MediaItem {
    val mediaBuilder = remember(url) { MediaItem.Builder() }
    mediaBuilder.setUri(url)
    return mediaBuilder.build()
}