package com.dariusz.twirplyapp.presentation.components.tweets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
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
fun DisplayTweetSeparate(
    tweetContent: Tweet?,
    authorInfo: UserMinimum?,
    includesFromResponse: Includes?,
    navController: NavController?
) = TweetBuilderSeparate(
    tweetContentFromResponse = tweetContent,
    authorInfoFromResponse = authorInfo,
    includesFromResponse = includesFromResponse,
    authorProfilePicture = { AuthorPicture(it) },
    authorandTweetInformation = { author, tweet ->
        AuthorInfoAndOther(
            author,
            tweet
        )
    },
    tweetIconContent = { tweet, nav ->
        TweetActions(tweet, nav)
    },
    tweetDivider = { Divider(thickness = 0.5.dp) },
    actionOnClick = {
        navigateToWithArgument(
            navController!!,
            Screens.AppScreens.TweetScreen.route,
            it
        )
    },
    actionOpenProfile = {
        navigateToWithArgument(
            navController!!,
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    },
    navController = navController!!
)

@ExperimentalCoilApi
@Composable
fun TweetBuilderSeparate(
    tweetContentFromResponse: Tweet?,
    authorInfoFromResponse: UserMinimum?,
    includesFromResponse: Includes?,
    authorProfilePicture: @Composable (UserMinimum) -> Unit,
    authorandTweetInformation: @Composable (UserMinimum, Tweet) -> Unit,
    tweetIconContent: @Composable (Tweet, NavController) -> Unit,
    tweetDivider: @Composable () -> Unit,
    actionOnClick: (String) -> Unit,
    actionOpenProfile: (String) -> Unit,
    navController: NavController
) {
    Row {
        if (authorInfoFromResponse != null) {
            authorProfilePicture(authorInfoFromResponse)
        }
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            if (authorInfoFromResponse != null && tweetContentFromResponse != null) {
                Column(Modifier.clickable(enabled = true, onClick = {
                    authorInfoFromResponse.id.let {
                        actionOpenProfile.invoke(
                            it
                        )
                    }
                })) {
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
                    DisplayMainContent(tweetContentFromResponse, includesFromResponse)
                }
            }
            if (tweetContentFromResponse != null) {
                tweetIconContent.invoke(tweetContentFromResponse, navController)
            }
            tweetDivider.invoke()
        }
    }
}


