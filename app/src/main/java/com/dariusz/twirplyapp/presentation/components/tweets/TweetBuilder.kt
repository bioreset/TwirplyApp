package com.dariusz.twirplyapp.presentation.components.tweets

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.*

@Composable
fun DisplayTweet(
    tweetContent: GenericResponse<Tweet?, Includes?, Errors?, Meta?>,
    navController: NavController
) = TweetBuilder(
    tweetContentFromResponse = tweetContent,
    authorProfilePicture = { AuthorPicture(it) },
    authorandTweetInformation = { author, tweet, tweetincludes ->
        AuthorInfoAndOther(
            author,
            tweet,
            tweetincludes
        )
    },
    tweetIconContent = { tweet, nav ->
        TweetActions(tweet, nav)
    },
    tweetDivider = { Divider(thickness = 0.5.dp) },
    navController = navController
)

@Composable
fun TweetBuilder(
    tweetContentFromResponse: GenericResponse<Tweet?, Includes?, Errors?, Meta?>,
    authorProfilePicture: @Composable (UserMinimum) -> Unit,
    authorandTweetInformation: @Composable (UserMinimum, Tweet, Includes) -> Unit,
    tweetIconContent: @Composable (Tweet, NavController) -> Unit,
    tweetDivider: @Composable () -> Unit,
    navController: NavController
) {
    val tweetPostContent = tweetContentFromResponse.outputOne
    val authorInformation = tweetContentFromResponse.outputTwo?.user?.get(0)

    val tweetIncludes = tweetContentFromResponse.outputTwo

    val tweetError = tweetContentFromResponse.outputThree

    Row {
        if (tweetError == null) {
            if (authorInformation != null) {
                authorProfilePicture(authorInformation)
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                if (authorInformation != null && tweetPostContent != null) {
                    if (tweetIncludes != null) {
                        authorandTweetInformation.invoke(
                            authorInformation,
                            tweetPostContent,
                            tweetIncludes
                        )
                    }
                    TweetMainContent(
                        tweetData = tweetPostContent,
                        tweetIncludes = tweetIncludes,
                        tweetDisplayText = {
                            it.entities?.let { it1 ->
                                HashtagsAndMentionsInTweet(
                                    it.content,
                                    it1,
                                    navController
                                )
                            }
                            Text(text = it.content, style = MaterialTheme.typography.body1)
                        },
                        tweetDisplayImage = {
                            TweetImage(it)
                        },
                        tweetDisplayMedia = {
                            tweetIncludes?.media?.get(0).let { mediaObject ->
                                if (mediaObject != null) {
                                    TweetMedia(mediaObject)
                                }
                            }
                        },
                        tweetDisplayPoll = {
                            tweetIncludes?.poll?.get(0).let { pollObject ->
                                if (pollObject != null) {
                                    TweetPoll(pollObject)
                                }
                            }
                        },
                        tweetDisplayMentionedTweet = {
                            TweetMentioned(it)
                        }
                    )
                    tweetIconContent.invoke(tweetPostContent, navController)
                    tweetDivider.invoke()
                }
            }
        } else {
            Text("Errors: ")
            Text(tweetError.toString())
        }
    }
}

