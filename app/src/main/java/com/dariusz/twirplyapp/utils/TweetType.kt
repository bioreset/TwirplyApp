package com.dariusz.twirplyapp.utils

import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.Tweet

object TweetType {

    fun determineTweetType(apiResponse: GenericResponse<Tweet?, Includes?, Error?, Nothing>): String =
        when {
            apiResponse.outputTwo?.poll?.id?.isNotEmpty() == true -> {
                "text+poll"
            }
            apiResponse.outputTwo?.media?.type?.isNotEmpty() == true -> {
                "text+media"
            }
            apiResponse.outputTwo?.place?.id?.isNotEmpty() == true -> {
                "text+media"
            }
            else -> {
                "onlytext"
            }
        }
    }

