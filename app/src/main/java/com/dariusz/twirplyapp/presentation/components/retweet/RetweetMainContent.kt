package com.dariusz.twirplyapp.presentation.components.retweet

import androidx.compose.runtime.*
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.*

@ExperimentalCoilApi
@Composable
fun DisplayRetweetMainContent(
    inputTweet: Tweet,
    inputIncludes: Includes,
    navController: NavController
) {
    RetweetMainContentBuilder(
        tweetData = inputTweet,
        tweetIncludes = inputIncludes,
        tweetDisplayText = { tweet, entity, nav ->
            RetweetTextContent(tweet, entity, nav)
        },
        tweetDisplayLink = {
            RetweetUrlObject(it)
        },
        tweetDisplayImage = {
            RetweetImage(it)
        },
        tweetDisplayMedia = { url, media ->
            RetweetMedia(url, media)
        },
        tweetDisplayPoll = {
            RetweetPoll(it)
        },
        navController
    )
}


@Composable
fun RetweetMainContentBuilder(
    tweetData: Tweet?,
    tweetIncludes: Includes?,
    tweetDisplayText: @Composable (Tweet, Entity, NavController) -> Unit,
    tweetDisplayLink: @Composable (UrlObject) -> Unit,
    tweetDisplayImage: @Composable (Media) -> Unit,
    tweetDisplayMedia: @Composable (String, Media) -> Unit,
    tweetDisplayPoll: @Composable (Poll) -> Unit,
    navController: NavController
) {

    tweetData?.let { tweetDataElement ->
        tweetDataElement.entities?.let { entities ->
            tweetDisplayText.invoke(tweetDataElement, entities, navController)
        }
    }

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
                    linkShared?.get(0)?.let { tweetDisplayMedia.invoke(it.fullUrl, mediaObject) }
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
        }
    }

    if (linkShared != null) {
        if (linkShared.size < 2 && !linkStatus) {
            val link = linkShared[0]
            tweetDisplayLink.invoke(link)
        }
    }

}