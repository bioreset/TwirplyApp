package com.dariusz.twirplyapp.domain.repository.tweet

import com.dariusz.twirplyapp.data.remote.api.tweet.TwirplyAppApiTweetService
import com.dariusz.twirplyapp.domain.model.*
import javax.inject.Inject

interface TweetRepository {

    suspend fun returnAllTweetInfo(
        tweetID: String,
        token: String
    ): GenericResponse<Tweet?, Includes?, Errors?, Meta?>

    suspend fun returnTweetsOfUser(
        userID: String,
        token: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

    suspend fun returnMentionsOfUser(
        userID: String,
        token: String
    ): GenericResponse<List<Tweet>?, Includes?, Errors?, Meta?>

}

class TweetRepositoryImpl
@Inject constructor(
    private val apiTweetService: TwirplyAppApiTweetService
) : TweetRepository {

    override suspend fun returnAllTweetInfo(tweetID: String, token: String) =
        apiTweetService.getTweetDataBasedOnId(tweetID, token)

    override suspend fun returnTweetsOfUser(
        userID: String,
        token: String
    ) =
        apiTweetService.fetchTweetTimelineOfUserBasedOnID(userID, token)

    override suspend fun returnMentionsOfUser(
        userID: String,
        token: String
    ) =
        apiTweetService.fetchMentionsTimelineOfUserBasedOnID(userID, token)


}