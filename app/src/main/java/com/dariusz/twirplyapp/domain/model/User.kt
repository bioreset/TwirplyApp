package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "profile_image_url")
    val profileImageUrl: String,
    @field:Json(name = "description")
    val description: String,
    @field:Json(name = "verified")
    val isVerified: Boolean,
    @field:Json(name = "location")
    val location: String,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "pinned_tweet_id")
    val pinnedTweetID: String?,
    @field:Json(name = "public_metrics")
    val publicMetrics: PublicMetricsUser
)

@JsonClass(generateAdapter = true)
data class UserMinimum(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "name")
    val name: String,
    @field:Json(name = "username")
    val username: String,
    @field:Json(name = "profile_image_url")
    val profileImageUrl: String?,
    @field:Json(name = "verified")
    val isVerified: Boolean?
) {
    companion object {
        fun minimizeUser(user: User): UserMinimum =
            UserMinimum(
                user.id,
                user.name,
                user.username,
                user.profileImageUrl,
                user.isVerified
            )
    }
}

@JsonClass(generateAdapter = true)
data class PublicMetricsUser(
    @field:Json(name = "followers_count")
    val followersCount: Int,
    @field:Json(name = "following_count")
    val followingCount: Int,
    @field:Json(name = "tweet_count")
    val tweetCount: Int,
    @field:Json(name = "listed_count")
    val listedCount: Int
)
