package com.dariusz.twirplyapp.presentation.screens.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import javax.inject.Inject

class FeedScreenViewModelFactory
@Inject
constructor(
    private val tweetRepository: TweetRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FeedScreenViewModel(
            tweetRepository
        ) as T
    }
}