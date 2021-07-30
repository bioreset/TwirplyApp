package com.dariusz.twirplyapp.presentation.components.profile

import androidx.compose.runtime.Composable
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.UserMinimum
import com.dariusz.twirplyapp.presentation.components.common.CompactUserInfoList

@ExperimentalCoilApi
@Composable
fun FollowingList(input: List<UserMinimum>, actionOpenProfile: (String) -> Unit) =
    CompactUserInfoList(input, actionOpenProfile)