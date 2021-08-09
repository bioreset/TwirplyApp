package com.dariusz.twirplyapp.presentation.components.tweet.useractions

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import com.dariusz.twirplyapp.di.PreferencesModule.provideAppPreferences
import com.dariusz.twirplyapp.di.RepositoryModule.provideUserActionRepository
import com.dariusz.twirplyapp.domain.model.Tweet
import com.dariusz.twirplyapp.presentation.UserActionsViewModel
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.ViewModelUtils.composeViewModel

@Composable
fun TweetActions(
    tweet: Tweet,
    navController: NavController
) {

    val userActionsViewModel = composeViewModel {
        UserActionsViewModel(
            provideUserActionRepository()
        )
    }

    val currentContext = LocalContext.current

    val appPrefs = provideAppPreferences(currentContext)

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
        actionShowQuoteTweets = {},
        actionReply = {

        },
        actionRetweet = {

        },
        actionLike = {

        },
        actionShare = {

        }
    )
}
