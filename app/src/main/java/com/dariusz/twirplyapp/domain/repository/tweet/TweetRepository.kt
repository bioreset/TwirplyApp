package com.dariusz.twirplyapp.domain.repository.tweet

import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetService
import com.dariusz.twirplyapp.data.remote.api.user.TwirplyAppApiUserService
import com.dariusz.twirplyapp.domain.model.TweetWithAuthor
import com.dariusz.twirplyapp.utils.Constants.API_EXPANSIONS_MEDIA
import com.dariusz.twirplyapp.utils.Constants.API_EXPANSIONS_POLL
import javax.inject.Inject

interface TweetRepository {

    suspend fun returnAllTweetInfo(tweetID: Int): TweetWithAuthor

}

class TweetRepositoryImpl
@Inject constructor(
    private val apiTweetService: TwirplyAppApiTweetService,
    private val apiUserService: TwirplyAppApiUserService
) : TweetRepository {

    override suspend fun returnAllTweetInfo(tweetID: Int): TweetWithAuthor {
        val initialTweetResponse = apiTweetService.getTweetDataBasedOnId(tweetID, "")
        val initialUserMinimumResponse = apiUserService.getCompactUserDataBasedOnId(
            initialTweetResponse.outputOne?.authorID?.toInt() ?: 0
        )
        val attachmentInFetchedTweet = initialTweetResponse.outputOne?.attachments?.get(0)
        return when {
            attachmentInFetchedTweet?.pollIds != null -> {
                TweetWithAuthor(
                    apiTweetService.getTweetDataBasedOnId(tweetID, API_EXPANSIONS_POLL),
                    initialUserMinimumResponse
                )
            }
            attachmentInFetchedTweet?.mediaKeys != null -> {
                TweetWithAuthor(
                    apiTweetService.getTweetDataBasedOnId(tweetID, API_EXPANSIONS_MEDIA),
                    initialUserMinimumResponse
                )
            }
            else ->
                TweetWithAuthor(
                    initialTweetResponse,
                    initialUserMinimumResponse
                )
        }
    }
}