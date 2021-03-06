package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Includes(
    @field:Json(name = "polls")
    val poll: List<Poll>? = null,
    @field:Json(name = "places")
    val place: List<Place>? = null,
    @field:Json(name = "media")
    val media: List<Media>? = null,
    @field:Json(name = "tweets")
    val tweet: List<Tweet>? = null,
    @field:Json(name = "users")
    val user: List<UserMinimum>? = null
)
