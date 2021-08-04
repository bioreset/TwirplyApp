package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserActions(
    @field:Json(name = "retweeted")
    val retweet: Boolean? = false,
    @field:Json(name = "blocking")
    val block: Boolean? = false,
    @field:Json(name = "liked")
    val like: Boolean? = false,
    @field:Json(name = "following")
    val follow: Boolean? = false,
    @field:Json(name = "pending_follow")
    val pendingFollow: Boolean? = false
)
