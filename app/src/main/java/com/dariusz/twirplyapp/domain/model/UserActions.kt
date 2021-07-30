package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserActions(
    @field:Json(name = "retweeted")
    val retweet: Boolean?,
    @field:Json(name = "blocking")
    val block: Boolean?,
    @field:Json(name = "liked")
    val like: Boolean?,
    @field:Json(name = "following")
    val follow: Boolean?,
    @field:Json(name = "pending_follow")
    val pendingFollow: Boolean?
)
