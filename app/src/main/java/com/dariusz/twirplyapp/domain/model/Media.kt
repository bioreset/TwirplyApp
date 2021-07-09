package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Media(
    @field:Json(name = "duration_ms")
    val durationInMs: Int?,
    @field:Json(name = "height")
    val height: Int,
    @field:Json(name = "width")
    val width: Int,
    @field:Json(name = "preview_image_url")
    val previewImageUrl: String,
    @field:Json(name = "type")
    val type: String,
    @field:Json(name = "public_metrics")
    val publicMetrics: PublicMetrics?
)

@JsonClass(generateAdapter = true)
data class PublicMetrics(
    @field:Json(name = "view_count")
    val viewCount: Int
)