package com.dariusz.twirplyapp.presentation.screens.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import com.dariusz.twirplyapp.domain.repository.user.UserRepository
import javax.inject.Inject

class ProfileScreenViewModelFactory
@Inject
constructor(
    private val userRepository: UserRepository,
    private val tweetRepository: TweetRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileScreenViewModel(
            userRepository,
            tweetRepository
        ) as T
    }
}