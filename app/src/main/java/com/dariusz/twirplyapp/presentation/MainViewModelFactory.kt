package com.dariusz.twirplyapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dariusz.twirplyapp.domain.repository.tweet.TweetRepository
import javax.inject.Inject

class MainViewModelFactory
@Inject
constructor(
    private val tweetRepository: TweetRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MainViewModel(
            tweetRepository
        ) as T
    }
}