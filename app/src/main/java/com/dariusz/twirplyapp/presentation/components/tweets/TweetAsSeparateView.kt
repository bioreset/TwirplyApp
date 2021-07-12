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
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.domain.model.UserMinimum
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun DisplayTweetSeparate(
    tweetContent: Tweet?,
    authorInfo: UserMinimum?,
    navController: NavController
) = TweetBuilderSeparate(
    tweetContentFromResponse = tweetContent,
    authorInfoFromResponse = authorInfo,
    authorProfilePicture = { AuthorPicture(it) },
    authorandTweetInformation = {
        AuthorInfoAndOther(
            authorInfo,
            tweetContent,
            null
        )
    },
    tweetIconContent = { TweetActions(it) },
    tweetDivider = { Divider(thickness = 0.5.dp) },
    actionOnClick = {
        navigateToWithArgument(
            navController,
            Screens.AppScreens.TweetScreen.route,
            it.toString()
        )
    }
)

@Composable
fun TweetBuilderSeparate(
    tweetContentFromResponse: Tweet?,
    authorInfoFromResponse: UserMinimum?,
    authorProfilePicture: @Composable (UserMinimum) -> Unit,
    authorandTweetInformation: @Composable () -> Unit,
    tweetIconContent: @Composable (Tweet) -> Unit,
    tweetDivider: @Composable () -> Unit,
    actionOnClick: (Int) -> Unit
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
            authorandTweetInformation.invoke()
            if (tweetContentFromResponse != null) {
                TweetMainContent(
                    tweetData = tweetContentFromResponse,
                    tweetIncludes = null,
                    tweetDisplayTextOnly = {
                        Text(text = it.content, style = MaterialTheme.typography.body1)
                    },
                    tweetDisplayImage = {
                        TweetImage(it)
                    },
                    tweetDisplayPoll = {},
                    tweetDisplayMedia = {}
                )
            }
            if (tweetContentFromResponse != null) {
                tweetIconContent.invoke(tweetContentFromResponse)
            }
            tweetDivider.invoke()
        }
    }
}


