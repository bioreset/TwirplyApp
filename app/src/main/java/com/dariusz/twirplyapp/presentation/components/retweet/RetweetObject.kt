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
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@ExperimentalCoilApi
@Composable
fun ShowRetweet(
    response: GenericResponse<Tweet?, Includes?, Errors?, Meta?>,
    navController: NavController
) {
    val tweetContent = response.outputOne
    val includes = response.outputTwo
    val authorInfo = includes?.user?.get(0)

    DisplayRetweetObject(
        tweetContent = tweetContent,
        authorInfo = authorInfo,
        includesFromResponse = includes,
        navController = navController
    )

}

@ExperimentalCoilApi
@Composable
fun DisplayRetweetObject(
    tweetContent: Tweet?,
    authorInfo: UserMinimum?,
    includesFromResponse: Includes?,
    navController: NavController
) = RetweetObjectBuilder(
    tweetContentFromResponse = tweetContent,
    authorInfoFromResponse = authorInfo,
    includesFromResponse = includesFromResponse,
    authorProfilePicture = { AuthorPictureRetweet(it) },
    authorandTweetInformation = { author, tweet ->
        AuthorInfoAndOtherRetweet(
            author,
            tweet
        )
    },
    actionOpenFullTweet = {
        navController.navigateToWithArgument(
            Screens.AppScreens.ProfileScreen.route,
            it
        )
    },
    navController = navController
)

@ExperimentalCoilApi
@Composable
fun RetweetObjectBuilder(
    tweetContentFromResponse: Tweet?,
    authorInfoFromResponse: UserMinimum?,
    includesFromResponse: Includes?,
    authorProfilePicture: @Composable (UserMinimum) -> Unit,
    authorandTweetInformation: @Composable (UserMinimum, Tweet) -> Unit,
    actionOpenFullTweet: (String) -> Unit,
    navController: NavController
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(enabled = true, onClick = {
                if (tweetContentFromResponse != null) {
                    actionOpenFullTweet.invoke(tweetContentFromResponse.id)
                }
            })
    )
    {
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
                DisplayRetweetMainContent(
                    tweetContentFromResponse,
                    includesFromResponse,
                    navController
                )
            }
        }
    }
}


