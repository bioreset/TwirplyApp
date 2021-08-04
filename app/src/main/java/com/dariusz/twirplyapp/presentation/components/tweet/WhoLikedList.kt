package com.dariusz.twirplyapp.presentation.components.tweet

import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.UserMinimum
import com.dariusz.twirplyapp.presentation.components.common.CompactUserInfoList

@ExperimentalCoilApi
@Composable
fun WhoLikedList(input: List<UserMinimum>, actionOpenProfile: (String) -> Unit) =
    CompactUserInfoList("Likes", input, actionOpenProfile)