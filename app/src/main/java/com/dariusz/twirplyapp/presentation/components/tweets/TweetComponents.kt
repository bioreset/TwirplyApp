package com.dariusz.twirplyapp.presentation.components.tweets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.theme.ThemeShapes
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography
import com.dariusz.twirplyapp.utils.DateUtils.formatDate

@Composable
fun AuthorPicture(userInfo: UserMinimum) {
    Image(
        painter = rememberImagePainter(
            data = userInfo.profileImageUrl,
            imageLoader = LocalImageLoader.current
        ),
        contentDescription = null,
        modifier = Modifier
            .size(40.dp)
            .clip(shape = RoundedCornerShape(40.dp))
    )
}

@Composable
fun AuthorInfoAndOther(userInfo: UserMinimum?, tweetInfo: Tweet?) {
    if (userInfo != null && tweetInfo != null) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = userInfo.name,
                style = ThemeTypography.h2,
                modifier = Modifier.padding(end = 3.dp)
            )

            if (userInfo.isVerified) Icon(
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
                text = " @" + userInfo.username + " ᛫ " + formatDate(tweetInfo.createdAt) +
                        " ᛫ " + tweetInfo.sourceApp,
                style = ThemeTypography.caption,
                textAlign = TextAlign.Left
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Composable
fun TweetIconSection(
    tweet: Tweet,
    actionReply: (Tweet) -> Unit,
    actionRetweet: (Tweet) -> Unit,
    actionLike: (Tweet) -> Unit,
    actionShare: (Tweet) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        IconButton(onClick = { actionReply.invoke(tweet) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.publicMetrics.replyCount.toString(),
                    modifier = Modifier.padding(start = 6.dp, bottom = 1.dp),
                    color = Color.LightGray,
                    style = ThemeTypography.body1
                )
            }
        }
        IconButton(onClick = { actionRetweet.invoke(tweet) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.publicMetrics.retweetCount.toString(),
                    modifier = Modifier.padding(start = 6.dp, bottom = 1.dp),
                    color = Color.LightGray,
                    style = ThemeTypography.body1
                )
            }
        }
        IconButton(onClick = { actionLike.invoke(tweet) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.publicMetrics.likesCount.toString(),
                    modifier = Modifier.padding(start = 6.dp, bottom = 1.dp),
                    color = Color.LightGray,
                    style = ThemeTypography.body1
                )
            }
        }
        IconButton(onClick = { actionShare.invoke(tweet) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.publicMetrics.quoteCount.toString(),
                    modifier = Modifier.padding(start = 6.dp, bottom = 1.dp),
                    color = Color.LightGray,
                    style = ThemeTypography.body1
                )
            }
        }
    }
}

@Composable
fun TweetImage(media: Media) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(
                data = media.url,
                imageLoader = LocalImageLoader.current,
                onExecute = { _, _ -> true },
                builder = {
                    crossfade(true)
                    allowHardware(false)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 120.dp)
                .padding(all = 3.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TweetPoll(poll: Poll) {
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
                "Votes amount: ${counter.value} ᛫ Ending time: ${formatDate(poll.endTime)}",
                style = ThemeTypography.caption, textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun TweetMedia(media: Media) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(
                data = media.previewImageUrl,
                imageLoader = LocalImageLoader.current,
                onExecute = { _, _ -> true },
                builder = {
                    crossfade(true)
                    allowHardware(false)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 120.dp)
                .padding(all = 3.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
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
fun TweetMentioned(tweetData: Tweet, author: UserMinimum) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .border(width = 0.25.dp, color = Color.Gray, shape = ThemeShapes.small)
    ) {
        DisplayTweetSeparate(tweetData, author, null, null)
    }
}

@ExperimentalCoilApi
@Composable
fun TweetUrlObject(urlObject: UrlObject) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Image(
            painter = rememberImagePainter(
                data = urlObject.images?.get(0)?.url,
                imageLoader = LocalImageLoader.current,
                onExecute = { _, _ -> true },
                builder = {
                    crossfade(true)
                    allowHardware(false)
                }
            ),
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 120.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
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
fun TweetActions(tweet: Tweet, navController: NavController) =
    TweetIconSection(
        tweet = tweet,
        actionReply = { /*TODO*/ },
        actionRetweet = { /*TODO*/ },
        actionLike = { /*TODO*/ },
        actionShare = { /*TODO*/ }
    )