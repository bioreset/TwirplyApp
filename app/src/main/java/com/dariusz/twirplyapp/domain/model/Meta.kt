package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Meta(
    @field:Json(name = "newest_id")
    val newestID: String,
    @field:Json(name = "oldest_id")
    val oldestID: String,
    @field:Json(name = "result_count")
    val resultCount: Int,
    @field:Json(name = "next_token")
    val nextToken: String
)