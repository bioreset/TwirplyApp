package com.dariusz.twirplyapp.presentation.components.tweets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
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
import coil.transform.CircleCropTransformation
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.utils.DateUtils.formatDate
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun AuthorPicture(userInfo: UserMinimum) {
    Image(
        painter = rememberCoilPainter(
            request = userInfo.profileImageUrl,
            requestBuilder = {
                transformations(CircleCropTransformation())
            },
        ),
        contentDescription = userInfo.username + "'s profile picture",
    )
}

@Composable
fun AuthorInfoAndOther(userInfo: UserMinimum?, tweetInfo: Tweet?, includesData: Includes?) {
    if (userInfo != null && tweetInfo != null)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = userInfo.name,
                style = typography.h6,
                modifier = Modifier.padding(end = 4.dp)
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

            Text(
                text = userInfo.username + " . " + formatDate(tweetInfo.createdAt) +
                        " . " + tweetInfo.sourceApp + " . " + (includesData?.place?.fullName
                    ?: ""),
                modifier = Modifier.padding(start = 8.dp),
                style = typography.body1,
                textAlign = TextAlign.Center
            )
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
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
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
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
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
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
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
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = { actionShare.invoke(tweet) }) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.LightGray
            )
            Text(
                text = tweet.publicMetrics.quoteCount.toString(),
                modifier = Modifier.padding(start = 8.dp),
                color = Color.LightGray,
                style = typography.caption
            )
        }
    }

}

@Composable
fun TweetImage(tweet: Tweet) {
    val displayUrl = tweet.entities[0].urls?.get(0)?.displayUrl
    if (displayUrl != null) {
        Image(
            painter = rememberCoilPainter(
                request = displayUrl
            ),
            contentDescription = null,
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth()
                .height(150.dp)
                .clip(RoundedCornerShape(4.dp)),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
fun TweetPoll(poll: Poll) {
    val counter = remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
    ) {
        for (option in poll.options) {
            counter.value += option.votesAmount
            Button(
                onClick = { /*TODO*/ }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp)
            ) {
                Text(text = option.label + option.votesAmount + " / " + counter.value)
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 6.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text("Votes amount: ${counter.value}")
            Text("Time to end: ${formatDate(poll.endTime)}")
        }
    }
}

@Composable
fun TweetMedia(media: Media) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp)
    ) {
        Image(
            painter = rememberCoilPainter(
                request = media.previewImageUrl
            ),
            contentDescription = null,
            modifier = Modifier
                .heightIn(min = 120.dp)
                .fillMaxWidth(),
            contentScale = ContentScale.Crop
        )
        Spacer(Modifier.height(12.dp))
        Text(
            text = "${media.height}x${media.width} - ${media.type} - ${media.durationInMs}",
            style = typography.subtitle2
        )
    }
}

@Composable
fun TweetMentioned(tweetData: Tweet) {
//TODO: CREATE MENTIONED TWEET UI
}


@Composable
fun TweetMainContent(
    tweetData: Tweet,
    tweetIncludes: Includes?,
    tweetDisplayText: @Composable (Tweet) -> Unit,
    tweetDisplayImage: @Composable (Tweet) -> Unit,
    tweetDisplayMedia: @Composable (Tweet) -> Unit,
    tweetDisplayPoll: @Composable (Tweet) -> Unit,
    tweetDisplayMentionedTweet: @Composable (Tweet) -> Unit
) {
    val tweetEntitiesElement = tweetData.entities[0]
    if (tweetEntitiesElement.urls?.size == 0 &&
        tweetEntitiesElement.description.toString().isEmpty() &&
        tweetIncludes == null
    ) {
        tweetDisplayText.invoke(tweetData)
    } else if (tweetEntitiesElement.urls?.size == 1) {
        if (tweetIncludes != null) {
            when {
                tweetIncludes.media.toString().isNotEmpty() -> {
                    tweetDisplayText.invoke(tweetData)
                    tweetDisplayMedia.invoke(tweetData)
                }
                tweetIncludes.poll.toString().isNotEmpty() -> {
                    tweetDisplayText.invoke(tweetData)
                    tweetDisplayPoll.invoke(tweetData)
                }
            }
        } else {
            tweetDisplayText.invoke(tweetData)
            tweetDisplayImage.invoke(tweetData)
        }
    } else if (tweetEntitiesElement.mentions?.isNotEmpty() == true) {
        tweetDisplayText.invoke(tweetData)
        if (tweetIncludes != null) {
            tweetIncludes.tweet?.get(0)?.let { tweetDisplayMentionedTweet.invoke(it) }
        }
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