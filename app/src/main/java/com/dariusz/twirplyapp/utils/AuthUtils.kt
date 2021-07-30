package com.dariusz.twirplyapp.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import com.dariusz.twirplyapp.domain.model.AuthResponseInitial
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_ACCESS_TOKEN
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_ACCESS_TOKEN_SECRET
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_CONSUMER_KEY
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_CONSUMER_SECRET
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_POST_URL
import com.dariusz.twirplyapp.utils.Constants.API_AUTH_URL
import com.dariusz.twirplyapp.utils.Constants.API_URL
import java.net.URLEncoder
import java.time.Instant.now
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.streams.asSequence

object AuthUtils {

    fun String.escapeUrl() = URLEncoder.encode(this, "UTF-8")

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
        return Random().ints(32, 0, chars.length)
            .asSequence()
            .map(chars::get)
            .joinToString("")
    }

    @Composable
    fun OpenUrlForLoginAction(
        actionSendIntitialRequest: () -> Unit,
        inputFromApi: AuthResponseInitial,
        actionOpenBrowser: @Composable (String) -> Unit
    ) {
        actionSendIntitialRequest.invoke()
        val oauthToken = inputFromApi.oauthToken
        val oauthSecretToken = inputFromApi.oauthSecretToken
        val callbackConfirmed = inputFromApi.oauthCallbackConfirmed
        if (oauthToken == "" && oauthSecretToken == "" && callbackConfirmed == true) {
            val url = "$API_AUTH_URL?oauth_token=$oauthToken"
            actionOpenBrowser.invoke(url)
        }
    }

    fun <T, R> completeLoginActionWithDataFromCallbackUrl(
        inputFromCallback: T,
        sendRequestAndObtainVerifier: (T) -> Unit,
        responseWithAccessToken: R
    ): R {
        sendRequestAndObtainVerifier.invoke(inputFromCallback)
        return responseWithAccessToken
    }

    @SuppressLint("NewApi")
    private fun prepareBaseString(): String {
        val postpart = "POST&${API_URL}" + API_AUTH_POST_URL
        val main = "&oauth_consumer_key=\"$API_AUTH_CONSUMER_KEY\", " +
                "oauth_nonce=\"${generateRandomString()}\", " +
                "oauth_signature_method=\"HMAC-SHA1\", oauth_timestamp=\"${now()}\", " +
                "oauth_token=\"${API_AUTH_ACCESS_TOKEN}\", " +
                "oauth_version=\"1.0\""
        return (postpart + main).escapeUrl()
    }

    private fun prepareSigningKey(): String {
        return API_AUTH_CONSUMER_SECRET + API_AUTH_ACCESS_TOKEN_SECRET
    }

    @SuppressLint("NewApi")
    private fun createSignature(data: String, key: String): String {
        val sha256Hmac = Mac.getInstance("HmacSHA256")
        val secretKey = SecretKeySpec(key.toByteArray(), "HmacSHA256")
        sha256Hmac.init(secretKey)
        return Base64.getEncoder().encodeToString(sha256Hmac.doFinal(data.toByteArray()))
    }

    fun getTheSignature() = createSignature(
        data = prepareBaseString(),
        key = prepareSigningKey()
    )

}