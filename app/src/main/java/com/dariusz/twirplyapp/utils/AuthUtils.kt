package com.dariusz.twirplyapp.utils

import android.annotation.SuppressLint
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_CONSUMER_KEY
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_CONSUMER_SECRET
import java.util.*
import kotlin.streams.asSequence

object AuthUtils {

    @SuppressLint("NewApi")
    fun encodeBase64(input: String): String =
        Base64.getEncoder().encodeToString(input.encodeToByteArray())

    @SuppressLint("NewApi")
    fun decodeBase64(input: String): String =
        Base64.getDecoder().decode(input).toString()

    fun prepareBasicClientCredentials() =
        encodeBase64(
            "$API_AUTH_CONSUMER_KEY:$API_AUTH_CONSUMER_SECRET"
        )

    fun generateRandomString(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"
        val output = ""
        return Random().ints(32)
            .asSequence()
            .map(chars::get)
            .joinToString(output)
    }

}