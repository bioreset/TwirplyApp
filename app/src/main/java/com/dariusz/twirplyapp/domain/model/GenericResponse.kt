package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GenericResponse<out A, B, C, D>(
    @field:Json(name = "data")
    val outputOne: A? = null,
    @field:Json(name = "includes")
    val outputTwo: B? = null,
    @field:Json(name = "error")
    val outputThree: C? = null,
    @field:Json(name = "meta")
    val outputFour: D? = null
)
