package com.dariusz.twirplyapp.presentation.components.retweet

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.domain.model.UserMinimum
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@ExperimentalCoilApi
@Composable
fun DisplayRetweetSeparate(
    tweetContent: Tweet?,
    authorInfo: UserMinimum?,
    includesFromResponse: Includes?,
    navController: NavController?
) = navController?.let { it ->
    RetweetBuilderSeparate(
        tweetContentFromResponse = tweetContent,
        authorInfoFromResponse = authorInfo,
        includesFromResponse = includesFromResponse,
        authorProfilePicture = { author ->
            AuthorPictureRetweet(author)
        },
        authorandTweetInformation = { author, tweet ->
            AuthorInfoAndOtherRetweet(
                author,
                tweet
            )
        },
        actionOnClick = { id ->
            it.navigateToWithArgument(
                Screens.AppScreens.TweetScreen.route,
                id
            )
        },
        navController = it
    )
}

@ExperimentalCoilApi
@Composable
fun RetweetBuilderSeparate(
    tweetContentFromResponse: Tweet?,
    authorInfoFromResponse: UserMinimum?,
    includesFromResponse: Includes?,
    authorProfilePicture: @Composable (UserMinimum) -> Unit,
    authorandTweetInformation: @Composable (UserMinimum, Tweet) -> Unit,
    actionOnClick: (String) -> Unit,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = true, onClick = {
                if (tweetContentFromResponse != null) {
                    actionOnClick.invoke(tweetContentFromResponse.id)
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
                Column {
                    authorandTweetInformation.invoke(
                        authorInfoFromResponse,
                        tweetContentFromResponse
                    )

                }
            }
            if (tweetContentFromResponse != null && includesFromResponse != null) {
                Column(Modifier.clickable(enabled = true, onClick = {
                    tweetContentFromResponse.id.let {
                        actionOnClick.invoke(
                            it
                        )
                    }
                })) {
                    DisplayRetweetMainContent(
                        tweetContentFromResponse,
                        includesFromResponse,
                        navController
                    )
                }
            }
        }
    }
}


