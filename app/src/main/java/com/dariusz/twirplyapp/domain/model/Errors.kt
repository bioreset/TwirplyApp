package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Errors(
    @field:Json(name = "title")
    val title: String,
    @field:Json(name = "detail")
    val detail: String,
    @field:Json(name = "parameter")
    val parameter: String,
    @field:Json(name = "value")
    val value: String
)
