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
import com.dariusz.twirplyapp.domain.model.*

@Composable
fun DisplayTweet(
    tweetContent: GenericResponse<Tweet?, Includes?, Errors?, Nothing>,
    authorInfo: GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>
) = TweetBuilder(
    tweetContentFromResponse = tweetContent,
    authorInfoFromResponse = authorInfo,
    authorProfilePicture = { AuthorPicture(it) },
    authorandTweetInformation = {
        AuthorInfoAndOther(
            authorInfo.outputOne,
            tweetContent.outputOne,
            tweetContent.outputTwo
        )
    },
    tweetIconContent = { TweetActions(it) },
    tweetDivider = { Divider(thickness = 0.5.dp) }
)

@Composable
fun TweetBuilder(
    tweetContentFromResponse: GenericResponse<Tweet?, Includes?, Errors?, Nothing>,
    authorInfoFromResponse: GenericResponse<UserMinimum?, Includes?, Errors?, Nothing>,
    authorProfilePicture: @Composable (UserMinimum) -> Unit,
    authorandTweetInformation: @Composable () -> Unit,
    tweetIconContent: @Composable (Tweet) -> Unit,
    tweetDivider: @Composable () -> Unit
) {
    val tweetPostContent = tweetContentFromResponse.outputOne
    val authorInformation = authorInfoFromResponse.outputOne

    val tweetIncludes = tweetContentFromResponse.outputTwo

    val tweetError = tweetContentFromResponse.outputThree
    val authorError = authorInfoFromResponse.outputThree

    Row {
        if (tweetError == null && authorError == null) {
            if (authorInformation != null) {
                authorProfilePicture(authorInformation)
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
            ) {
                if (authorInformation != null && tweetPostContent != null) {
                    authorandTweetInformation.invoke()
                    TweetMainContent(
                        tweetData = tweetPostContent,
                        tweetIncludes = tweetIncludes,
                        tweetDisplayTextOnly = {
                            Text(text = it.content, style = MaterialTheme.typography.body1)
                        },
                        tweetDisplayImage = {
                            TweetImage(it)
                        },
                        tweetDisplayMedia = {
                            tweetIncludes?.media?.let { mediaObject -> TweetMedia(mediaObject) }
                        },
                        tweetDisplayPoll = {
                            tweetIncludes?.poll?.let { pollObject -> TweetPoll(pollObject) }
                        }
                    )
                    tweetIconContent.invoke(tweetPostContent)
                    tweetDivider.invoke()
                }
            }
        } else {
            Text("Errors: ")
            Text(tweetError.toString())
            Text(authorError.toString())
        }
    }
}
