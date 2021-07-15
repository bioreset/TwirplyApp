package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Place(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "country_code")
    val countryCode: String,
    @field:Json(name = "full_name")
    val fullName: String,
    @field:Json(name = "place_type")
    val placeType: String
)
