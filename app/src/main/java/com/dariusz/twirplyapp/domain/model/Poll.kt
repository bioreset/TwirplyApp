package com.dariusz.twirplyapp.domain.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Poll(
    @field:Json(name = "id")
    val id: String,
    @field:Json(name = "duration_minutes")
    val durationInMinutes: Int,
    @field:Json(name = "end_datetime")
    val endTime: String,
    @field:Json(name = "options")
    val options: List<PollOptions>,
    @field:Json(name = "voting_status")
    val votingStatus: String
)

@JsonClass(generateAdapter = true)
data class PollOptions(
    @field:Json(name = "position")
    val position: Int,
    @field:Json(name = "label")
    val label: String,
    @field:Json(name = "votes")
    val votesAmount: Int
)