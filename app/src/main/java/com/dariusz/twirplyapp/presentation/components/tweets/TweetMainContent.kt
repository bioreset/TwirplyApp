package com.dariusz.twirplyapp.presentation.components.tweets

import androidx.compose.material.Text
import androidx.compose.runtime.*
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography

@ExperimentalCoilApi
@Composable
fun DisplayMainContent(
    inputTweet: Tweet,
    inputIncludes: Includes
) {
    TweetMainContentBuilder(
        tweetData = inputTweet,
        tweetIncludes = inputIncludes,
        tweetDisplayText = { Text(text = it.content, style = ThemeTypography.body1) },
        tweetDisplayLink = { TweetUrlObject(it) },
        tweetDisplayImage = { TweetImage(it) },
        tweetDisplayMedia = { TweetMedia(it) },
        tweetDisplayPoll = { TweetPoll(it) },
        tweetDisplayMentionedTweet = { tweet, user ->
            TweetMentioned(tweet, user)
        })
}

@Composable
fun TweetMainContentBuilder(
    tweetData: Tweet?,
    tweetIncludes: Includes?,
    tweetDisplayText: @Composable (Tweet) -> Unit,
    tweetDisplayLink: @Composable (UrlObject) -> Unit,
    tweetDisplayImage: @Composable (Media) -> Unit,
    tweetDisplayMedia: @Composable (Media) -> Unit,
    tweetDisplayPoll: @Composable (Poll) -> Unit,
    tweetDisplayMentionedTweet: @Composable (Tweet, UserMinimum) -> Unit
) {

    tweetData?.let { tweetDisplayText.invoke(it) }

    val tweetAttachments = tweetData?.attachments
    val referencedTweets = tweetData?.referencedTweets
    val linkShared = tweetData?.entities?.urls

    var linkStatus by remember { mutableStateOf(false) }

    if (tweetAttachments != null) {
        if (tweetAttachments.pollIds != null) {
            val pollObject =
                tweetIncludes?.poll?.find {
                    tweetAttachments.pollIds[0] == it.id
                }
            if (pollObject != null) {
                tweetDisplayPoll.invoke(pollObject)
            }
        } else if (tweetAttachments.mediaKeys != null) {
            val mediaObject = tweetIncludes?.media?.find {
                tweetAttachments.mediaKeys[0] == it.mediaKey
            }

            if (mediaObject != null) {
                if (mediaObject.type == "video") {
                    tweetDisplayMedia.invoke(mediaObject)
                    Text("VIDEO_OBJECT")
                } else if (mediaObject.type == "photo") {
                    tweetDisplayImage.invoke(mediaObject)
                    linkStatus = true
                }
            }
        }
    }

    if (referencedTweets != null) {
        val referencedTweet = referencedTweets[0]
        if (referencedTweet.type == "retweeted") {
            val tweetObject = tweetIncludes?.tweet?.find {
                referencedTweet.tweetID == it.id
            }
            val authorObject = tweetIncludes?.user?.find {
                tweetObject?.authorID == it.id
            }
            if (tweetObject != null) {
                if (authorObject != null) {
                    tweetDisplayMentionedTweet.invoke(tweetObject, authorObject)
                }
            }
        }
    }

    if (linkShared != null) {
        if (linkShared.size < 2 && !linkStatus) {
            val link = linkShared[0]
            tweetDisplayLink.invoke(link)
        }
    }

}




