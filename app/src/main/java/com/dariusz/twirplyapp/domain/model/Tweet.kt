package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Tweet(
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "author_id")
    val authorID: String,
    @field:Json(name = "created_at")
    val createdAt: String,
    @field:Json(name = "entities")
    val entities: List<Entity>,
    @field:Json(name = "lang")
    val lang: String,
    @field:Json(name = "possibly_sensitive")
    val possiblySensitive: Boolean,
    @field:Json(name = "source")
    val sourceApp: String,
    @field:Json(name = "text")
    val content: String,
    @field:Json(name = "attachments")
    val attachments: List<Attachments>?,
    @field:Json(name = "referenced_tweets")
    val referencedTweets: List<ReferencedTweets>?,
    @field:Json(name = "public_metrics")
    val publicMetrics: PublicMetricsTweet
)

@JsonClass(generateAdapter = true)
data class Attachments(
    @field:Json(name = "poll_ids")
    val pollIds: List<String>?,
    @field:Json(name = "media_keys")
    val mediaKeys: List<String>?
)

@JsonClass(generateAdapter = true)
data class ReferencedTweets(
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "tweet_id")
    val tweetID: String
)

@JsonClass(generateAdapter = true)
data class PublicMetricsTweet(
    @field:Json(name = "retweet_count")
    val retweetCount: Int,
    @field:Json(name = "reply_count")
    val replyCount: Int,
    @field:Json(name = "like_count")
    val likesCount: Int,
    @field:Json(name = "quote_count")
    val quoteCount: Int
)
