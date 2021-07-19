package com.dariusz.twirplyapp.presentation.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.dariusz.twirplyapp.domain.model.*
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.pagerTabIndicatorOffset
import kotlinx.coroutines.launch

@ExperimentalPagerApi
@ExperimentalCoilApi
@Composable
fun ProfileFeed(
    user: GenericResponse<User?, Includes?, Errors?, Meta?>,
    navController: NavController,
    pagerState: PagerState,
    tweets: @Composable () -> Unit,
    mentions: @Composable () -> Unit,
    actionFollow: (String) -> Unit
) {
    val userInfoFull = user.outputOne

    if (userInfoFull != null) {
        Column(
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp)
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Avatar(user = userInfoFull)
                FollowButton { actionFollow.invoke(userInfoFull.id) }
            }
            UserInfo(user = userInfoFull, navController)
            //    PinnedTweet(user, navController)
            Tabs(pagerState)
            TabsContent(
                pagerState = pagerState,
                tweets = { tweets.invoke() },
                mentions = { mentions.invoke() }
            )
        }
    }
}

@Composable
fun UserInfo(user: User, navController: NavController) {
    Column {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = user.name,
                style = ThemeTypography.h2
            )

            if (user.isVerified) Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                tint = Color(0xFF1DA1F2),
                modifier = Modifier
                    .size(20.dp)
                    .align(Alignment.CenterVertically)
                    .padding(start = 4.dp)
            )
        }

        Text(
            text = "@" + user.username,
            style = ThemeTypography.caption
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = user.description,
            style = ThemeTypography.body2
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
    Row(
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            Text(
                text = "${user.publicMetrics.followingCount} ",
                style = ThemeTypography.h3
            )
            Text(
                text = "Following ",
                style = ThemeTypography.body2,
                modifier = Modifier
                    .clickable(onClick = {
                        navigateToWithArgument(
                            navController,
                            Screens.AppScreens.FollowingScreen.route,
                            user.id
                        )
                    })
            )
        }
        Row {
            Text(
                text = " ${user.publicMetrics.followersCount} ",
                style = ThemeTypography.h3
            )
            Text(
                text = "Followers",
                style = ThemeTypography.body2,
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
}

@Composable
private fun Avatar(user: User) {
    Image(
        painter = rememberImagePainter(
            data = user.profileImageUrl,
            imageLoader = LocalImageLoader.current,
            onExecute = { _, _ -> true },
            builder = {
                crossfade(true)
                allowHardware(false)
            }
        ),
        contentDescription = null,
        modifier = Modifier
            .size(50.dp)
            .clip(shape = RoundedCornerShape(40.dp))
            .border(
                border = BorderStroke(width = 4.dp, color = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(40.dp)
            )
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
        border = BorderStroke(1.dp, MaterialTheme.colors.primary),
        modifier = Modifier.padding(top = 30.dp)
    ) {
        Text(text = "Follow", style = ThemeTypography.body2)
    }
}

@ExperimentalPagerApi
@Composable
fun Tabs(pagerState: PagerState) {
    val scope = rememberCoroutineScope()
    ScrollableTabRow(
        selectedTabIndex = pagerState.currentPage,
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Tab(
            text = { Text("Tweets", style = ThemeTypography.body1) },
            selected = pagerState.currentPage == 0,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(0)
                }
            }
        )
        Tab(
            text = { Text("Tweets and mentions", style = ThemeTypography.body1) },
            selected = pagerState.currentPage == 1,
            onClick = {
                scope.launch {
                    pagerState.animateScrollToPage(1)
                }
            }
        )
    }
}

@ExperimentalPagerApi
@Composable
fun TabsContent(
    pagerState: PagerState,
    tweets: @Composable () -> Unit,
    mentions: @Composable () -> Unit
) {
    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> {
                tweets.invoke()
            }
            1 -> {
                mentions.invoke()
            }
        }
    }
}
