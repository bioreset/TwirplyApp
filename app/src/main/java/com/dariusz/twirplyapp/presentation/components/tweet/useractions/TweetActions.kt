package com.dariusz.twirplyapp.presentation.components.tweet.useractions

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun TweetActions(
    tweet: Tweet,
    navController: NavController
) {

    TweetIconSection(
        tweet = tweet,
        actionShowWhoLikedTweet = {
            navController.navigateToWithArgument(
                Screens.AppScreens.WhoLikedTweet.route,
                it
            )
        },
        actionShowUsersWhoRetweeted = {
            navController.navigateToWithArgument(
                Screens.AppScreens.RetweetsScreen.route,
                it
            )
        },
        actionShowQuoteTweets = { },
        actionReply = { /*TODO*/ },
        actionRetweet = { /*TODO*/ },
        actionLike = { /*TODO*/ },
        actionShare = { /*TODO*/ }
    )
}
