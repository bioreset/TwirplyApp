package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Includes(
    @field:Json(name = "polls")
    val poll: Poll? = null,
    @field:Json(name = "places")
    val place: Place? = null,
    @field:Json(name = "media")
    val media: Media? = null,
    @field:Json(name = "tweets")
    val tweet: List<Tweet>? = null,
    @field:Json(name = "users")
    val user: List<User>? = null
)
