package com.dariusz.twirplyapp.domain.repository.tweet

import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetService
import com.dariusz.twirplyapp.domain.model.Errors
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.Tweet
import javax.inject.Inject

interface TweetRepository {

    suspend fun returnAllTweetInfo(tweetID: Int): GenericResponse<Tweet?, Includes?, Errors?, Nothing>

}

class TweetRepositoryImpl
@Inject constructor(
    private val apiTweetService: TwirplyAppApiTweetService
): TweetRepository {

    override suspend fun returnAllTweetInfo(tweetID: Int) =
        apiTweetService.getTweetDataBasedOnId(tweetID)

}