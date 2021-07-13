package com.dariusz.twirplyapp.presentation.components.tweets

import androidx.compose.foundation.clickable
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
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.domain.model.UserMinimum
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun DisplayTweetSeparate(
    tweetContent: Tweet?,
    authorInfo: UserMinimum?,
    includesFromResponse: Includes?,
    navController: NavController
) = TweetBuilderSeparate(
    tweetContentFromResponse = tweetContent,
    authorInfoFromResponse = authorInfo,
    includesFromResponse = includesFromResponse,
    authorProfilePicture = { AuthorPicture(it) },
    authorandTweetInformation = { author, tweet ->
        AuthorInfoAndOther(
            author,
            tweet,
            null
        )
    },
    tweetIconContent = { tweet, nav ->
        TweetActions(tweet, nav)
    },
    tweetDivider = { Divider(thickness = 0.5.dp) },
    actionOnClick = {
        navigateToWithArgument(
            navController,
            Screens.AppScreens.TweetScreen.route,
            it.toString()
        )
    },
    navController = navController
)

@Composable
fun TweetBuilderSeparate(
    tweetContentFromResponse: Tweet?,
    authorInfoFromResponse: UserMinimum?,
    includesFromResponse: Includes?,
    authorProfilePicture: @Composable (UserMinimum) -> Unit,
    authorandTweetInformation: @Composable (UserMinimum, Tweet) -> Unit,
    tweetIconContent: @Composable (Tweet, NavController) -> Unit,
    tweetDivider: @Composable () -> Unit,
    actionOnClick: (Int) -> Unit,
    navController: NavController
) {
    Row(
        Modifier.clickable(enabled = true, onClick = {
            tweetContentFromResponse?.id?.let {
                actionOnClick.invoke(
                    it
                )
            }
        })
    ) {
        if (authorInfoFromResponse != null) {
            authorProfilePicture(authorInfoFromResponse)
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            if (authorInfoFromResponse != null && tweetContentFromResponse != null) {
                authorandTweetInformation.invoke(authorInfoFromResponse, tweetContentFromResponse)
            }
            if (tweetContentFromResponse != null) {
                TweetMainContent(
                    tweetData = tweetContentFromResponse,
                    tweetIncludes = null,
                    tweetDisplayText = {
                        HashtagsAndMentionsInTweet(it.content, it.entities[0], navController)
                        Text(text = it.content, style = MaterialTheme.typography.body1)
                    },
                    tweetDisplayImage = {
                        TweetImage(it)
                    },
                    tweetDisplayMedia = {
                        includesFromResponse?.media?.let { mediaObject -> TweetMedia(mediaObject) }
                    },
                    tweetDisplayPoll = {
                        includesFromResponse?.poll?.let { pollObject -> TweetPoll(pollObject) }
                    },
                    tweetDisplayMentionedTweet = {
                        TweetMentioned(it)
                    }
                )
            }
            if (tweetContentFromResponse != null) {
                tweetIconContent.invoke(tweetContentFromResponse, navController)
            }
            tweetDivider.invoke()
        }
    }
}


