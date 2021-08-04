package com.dariusz.twirplyapp.presentation.components.tweet.useractions

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography

@Composable
fun TweetIconSection(
    tweet: Tweet,
    actionShowWhoLikedTweet: (String) -> Unit,
    actionShowUsersWhoRetweeted: (String) -> Unit,
    actionShowQuoteTweets: (String) -> Unit,
    actionReply: (String) -> Unit,
    actionRetweet: (String) -> Unit,
    actionLike: (String) -> Unit,
    actionShare: (String) -> Unit
) {

    val tweetID = tweet.id

    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
        Text(
            text = tweet.publicMetrics.replyCount.toString() + " replies",
            modifier = Modifier.padding(3.dp),
            color = Color.LightGray,
            style = ThemeTypography.body1
        )
        Text(
            text = tweet.publicMetrics.retweetCount.toString() + " retweets",
            modifier = Modifier
                .padding(start = 6.dp, bottom = 1.dp)
                .clickable(enabled = true, onClick = {
                    actionShowUsersWhoRetweeted.invoke(tweetID)
                }),
            color = Color.LightGray,
            style = ThemeTypography.body1
        )
        Text(
            text = tweet.publicMetrics.likesCount.toString() + " likes",
            modifier = Modifier
                .padding(start = 6.dp, bottom = 1.dp)
                .clickable(enabled = true, onClick = {
                    actionShowWhoLikedTweet.invoke(tweetID)
                }),
            color = Color.LightGray,
            style = ThemeTypography.body1
        )
        Text(
            text = tweet.publicMetrics.quoteCount.toString() + " quote tweets",
            modifier = Modifier
                .padding(start = 6.dp, bottom = 1.dp)
                .clickable(enabled = true, onClick = {
                    actionShowQuoteTweets.invoke(tweetID)
                }),
            color = Color.LightGray,
            style = ThemeTypography.body1
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        IconButton(onClick = { actionReply.invoke(tweetID) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
            }
        }
        IconButton(onClick = { actionRetweet.invoke(tweetID) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
            }
        }
        LikeTweet(
            tweet,
            actionLike
        )
        IconButton(onClick = { actionShare.invoke(tweetID) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.Share,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun LikeTweet(
    tweet: Tweet,
    actionLike: (String) -> Unit
) {

    val likeStatus = remember { mutableStateOf(false) }

    if (!likeStatus.value) {

        IconButton(onClick = { actionLike.invoke(tweet.id) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
            }
        }
    } else {
        IconButton(onClick = { actionLike.invoke(tweet.id) }) {
            Row {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.Blue
                )
            }
        }
    }
}
