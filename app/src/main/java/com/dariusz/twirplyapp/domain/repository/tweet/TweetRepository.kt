package com.dariusz.twirplyapp.domain.repository.tweet

import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetService
import com.dariusz.twirplyapp.domain.model.*
import javax.inject.Inject

interface TweetRepository {

    suspend fun returnAllTweetInfo(tweetID: Int): GenericResponse<Tweet?, Includes?, Errors?, Nothing>

    suspend fun returnTweetsOfUser(
        userID: Int,
        paginationToken: String = ""
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>

    suspend fun returnMentionsOfUser(
        userID: Int,
        paginationToken: String = ""
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta>

}

class TweetRepositoryImpl
@Inject constructor(
    private val apiTweetService: TwirplyAppApiTweetService
) : TweetRepository {

    override suspend fun returnAllTweetInfo(tweetID: Int) =
        apiTweetService.getTweetDataBasedOnId(tweetID)

    override suspend fun returnTweetsOfUser(
        userID: Int,
        paginationToken: String
    ) =
        apiTweetService.fetchTweetTimelineOfUserBasedOnID(userID, "", paginationToken)

    override suspend fun returnMentionsOfUser(
        userID: Int,
        paginationToken: String
    ) =
        apiTweetService.fetchMentionsTimelineOfUserBasedOnID(userID, "", paginationToken)


}