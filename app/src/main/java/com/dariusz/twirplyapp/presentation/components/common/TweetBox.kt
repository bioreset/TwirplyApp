package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.transform.CircleCropTransformation
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.domain.model.UserMinimum
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun TweetBoxOnlyText(tweetContent: Tweet, userInfo: UserMinimum){
    Row {
        AuthorPicture(userInfo)
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            AuthorInfoAndOther(tweetContent, userInfo)
            Text(text = tweetContent.content, style = typography.body1)
            TweetIconSection(tweet = tweetContent)
            Divider(thickness = 0.5.dp)
        }
    }
}

@Composable
fun AuthorPicture(userInfo: UserMinimum){
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
fun AuthorInfoAndOther(tweetInfo: Tweet, userInfo: UserMinimum){
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(text = userInfo.name, style = typography.h6, modifier = Modifier.padding(end = 4.dp))
        Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFF1DA1F2),
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically)
                .padding(top = 2.dp)
        )
        Text(
            text = userInfo.username + " . " + tweetInfo.createdAt + " . posted from: " + tweetInfo.sourceApp,
            modifier = Modifier.padding(start = 8.dp),
            style = typography.body1,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TweetIconSection(tweet: Tweet) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick = {}) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_speech_bubble),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.entities.get(1).mentions.get(0). toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.ic_retweet_solid),
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.retweetCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp),
                    tint = Color.LightGray
                )
                Text(
                    text = tweet.likesCount.toString(),
                    modifier = Modifier.padding(start = 8.dp),
                    color = Color.LightGray,
                    style = typography.caption
                )
            }
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(16.dp),
                tint = Color.LightGray
            )
        }
    }

}


@Composable
private fun TweetImage(imageLink: String) {
    if (imageLink.isBlank()) {
        Image(
            painter = rememberCoilPainter(
                request = imageLink
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