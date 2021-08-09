package com.dariusz.twirplyapp.presentation.components.tweet.useractions

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.dariusz.twirplyapp.domain.model.Tweet

@Composable
fun ShareAction(
    tweet: Tweet
) {
    val currentContext = LocalContext.current
    val share = Intent.createChooser(Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TITLE, "Tweet ${tweet.id}")
        putExtra(Intent.EXTRA_TEXT, tweet.content)
    }, null)

    return currentContext.startActivity(share)

}