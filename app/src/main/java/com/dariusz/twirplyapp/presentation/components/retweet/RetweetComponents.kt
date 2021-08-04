package com.dariusz.twirplyapp.presentation.components.retweet

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.common.DisplayImage
import com.dariusz.twirplyapp.presentation.components.common.PlayVideo
import com.dariusz.twirplyapp.presentation.components.common.buildFullString
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography
import com.dariusz.twirplyapp.utils.DateUtils.countElapsedTime

@ExperimentalCoilApi
@Composable
fun AuthorPictureRetweet(userInfo: UserMinimum) {
    userInfo.profileImageUrl?.let {
        DisplayImage(
            url = it, modifier = Modifier
                .size(40.dp)
                .clip(shape = RoundedCornerShape(40.dp))
        )
    }
}

@SuppressLint("NewApi")
@Composable
fun AuthorInfoAndOtherRetweet(userInfo: UserMinimum?, tweetInfo: Tweet?) {
    if (userInfo != null && tweetInfo != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = userInfo.name,
                style = ThemeTypography.h2,
                modifier = Modifier.padding(end = 3.dp)
            )

            if (userInfo.isVerified == true) Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF1DA1F2),
                modifier = Modifier
                    .size(18.dp)
                    .align(Alignment.CenterVertically)
                    .padding(top = 2.dp)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Row {
            Text(
                text = " @" + userInfo.username + " ᛫ " + countElapsedTime(tweetInfo.createdAt) + " ago " +
                        " ᛫ " + tweetInfo.sourceApp,
                style = ThemeTypography.caption,
                textAlign = TextAlign.Left
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@ExperimentalCoilApi
@Composable
fun RetweetImage(media: Media) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        media.url?.let {
            DisplayImage(
                url = it,
                modifier = Modifier
                    .heightIn(min = 120.dp)
                    .padding(all = 3.dp)
                    .fillMaxWidth()
            )
        }
    }
}

@SuppressLint("NewApi")
@Composable
fun RetweetPoll(poll: Poll) {
    val counter = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        for (option in poll.options) {
            counter.value += option.votesAmount
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                Text(text = option.label, style = ThemeTypography.button)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(all = 3.dp)
        ) {
            Text(
                "Votes amount: ${counter.value} ᛫ Ending in ${countElapsedTime(poll.endTime)}",
                style = ThemeTypography.caption, textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun RetweetMedia(url: String, media: Media) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        PlayVideo(url = url, media = media)
        Spacer(Modifier.height(8.dp))
        Text(
            text = "Media: ${media.height}x${media.width} ᛫ ${media.type} ᛫ ${media.durationInMs} ᛫ ${media.previewImageUrl}",
            style = ThemeTypography.caption,
            modifier = Modifier.padding(all = 3.dp),
            textAlign = TextAlign.Center
        )
    }
}

@ExperimentalCoilApi
@Composable
fun RetweetUrlObject(urlObject: UrlObject) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        urlObject.images?.get(0)?.url?.let {
            DisplayImage(
                url = it,
                modifier = Modifier
                    .heightIn(min = 120.dp)
                    .fillMaxWidth()
            )
        }
        Spacer(Modifier.height(3.dp))
        urlObject.title?.let {
            Text(
                text = it,
                style = ThemeTypography.body1,
                modifier = Modifier.padding(all = 3.dp)

            )
        }
        urlObject.description?.let {
            Text(
                text = it,
                style = ThemeTypography.button,
                modifier = Modifier.padding(all = 3.dp)
            )
        }
        Text(
            text = urlObject.displayUrl,
            style = ThemeTypography.caption,
            modifier = Modifier.padding(all = 3.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun RetweetTextContent(tweet: Tweet, entity: Entity, navController: NavController) {
    val tweetContent = tweet.content
    val finalString = buildFullString(tweetContent, entity, navController)
    Text(text = finalString, style = ThemeTypography.body1)
}
