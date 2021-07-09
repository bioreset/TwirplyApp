package com.dariusz.twirplyapp.domain.model

data class GenericFetchTweetResponse(
    val data: Tweet? = null,
    val includes: Includes? = null,
    val errors: Errors? = null
)

data class GenericFetchMultipleTweetsResponse(
    val data: List<Tweet>? = null,
    val includes: Includes? = null,
    val errors: Errors? = null,
    val meta: Meta? = null
)

