package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Entity(
    @field:Json(name = "urls")
    val urls: List<UrlObject>?,
    @field:Json(name = "description")
    val description: Description?,
    @field:Json(name = "mentions")
    val mentions: List<Mentions>?,
    @field:Json(name = "hashtags")
    val hashtags: List<Hashtags>?
)

@JsonClass(generateAdapter = true)
data class UrlObject(
    @field:Json(name = "start")
    val start: Int,
    @field:Json(name = "end")
    val end: Int,
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "display_url")
    val displayUrl: String,
    @field:Json(name = "images")
    val images: List<ImageObject>,
    @field:Json(name = "status")
    val status: Int,
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "description")
    val description: String
)

@JsonClass(generateAdapter = true)
data class ImageObject(
    @field:Json(name = "url")
    val url: String,
    @field:Json(name = "width")
    val width: Int,
    @field:Json(name = "height")
    val height: Int
)

@JsonClass(generateAdapter = true)
data class Description(
    @field:Json(name = "hashtags")
    val hashtags: List<Hashtags>?
)

@JsonClass(generateAdapter = true)
data class Hashtags(
    @field:Json(name = "start")
    val start: Int,
    @field:Json(name = "end")
    val end: Int,
    @field:Json(name = "tag")
    val tag: String
)

@JsonClass(generateAdapter = true)
data class Mentions(
    @field:Json(name = "start")
    val start: Int,
    @field:Json(name = "end")
    val end: Int,
    @field:Json(name = "username")
    val username: String
)