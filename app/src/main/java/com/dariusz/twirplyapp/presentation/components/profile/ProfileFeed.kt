package com.dariusz.twirplyapp.presentation.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun ProfileFeed(
    user: GenericResponse<User?, Includes?, Errors?, Meta?>,
    tweets: @Composable (String) -> Unit,
    mentions: @Composable (String) -> Unit,
    navController: NavController,
    actionFollow: (String) -> Unit
) {
    val userInfoFull = user.outputOne
    Column {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Spacer(modifier = Modifier.height(44.dp))
            if (userInfoFull != null) {
                Avatar(user = userInfoFull)
                UserInfo(user = userInfoFull, navController)
                FollowButton { actionFollow.invoke(userInfoFull.id) }
                Spacer(modifier = Modifier.height(8.dp))
                TabbedTweetsAndMentions(userInfoFull.id,
                    tweets = {
                        tweets.invoke(it)
                    },
                    mentions = {
                        mentions.invoke(it)
                    }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        PinnedTweet(user, navController)
    }

}

@Composable
fun UserInfo(user: User, navController: NavController) {
    Text(
        text = user.name,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
    )
    Text(text = user.username)
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = user.description,
        style = TextStyle(fontSize = 14.sp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${user.publicMetrics.followingCount}",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Following",
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .clickable(onClick = {
                    navigateToWithArgument(
                        navController,
                        Screens.AppScreens.FollowingScreen.route,
                        user.id
                    )
                })
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "${user.publicMetrics.followersCount}",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Followers",
            style = TextStyle(fontSize = 14.sp),
            modifier = Modifier
                .clickable(onClick = {
                    navigateToWithArgument(
                        navController,
                        Screens.AppScreens.FollowersScreen.route,
                        user.id
                    )
                })
        )
    }
}

@Composable
private fun Avatar(user: User) {
    Image(
        painter = rememberImagePainter(
            data = user.profileImageUrl,
            imageLoader = LocalImageLoader.current
        ),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .clip(shape = RoundedCornerShape(40.dp))
            .border(
                border = BorderStroke(width = 4.dp, color = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(40.dp)
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun FollowButton(actionFollow: () -> Unit) {
    Button(
        onClick = {
            //TODO FOLLOW ACTION
            actionFollow.invoke()
        },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Text(text = "Follow")
    }
}

@Composable
fun TabbedTweetsAndMentions(
    userID: String,
    tweets: @Composable (String) -> Unit,
    mentions: @Composable (String) -> Unit
) {

    val currentlyShowing = remember(String) { mutableStateOf("tweets") }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Button(onClick = {
            currentlyShowing.value = "tweets"
        }) {
            Text(
                text = "Tweets"
            )
        }
        Button(onClick = {
            currentlyShowing.value = "mentions"
        }) {
            Text(
                text = "Tweets and Mentions"
            )
        }
    }
    if (currentlyShowing.value == "tweets") tweets.invoke(userID)
    else mentions.invoke(userID)

}
