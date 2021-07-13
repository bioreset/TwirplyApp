package com.dariusz.twirplyapp.presentation.components.tweets

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.Entity

@Composable
fun HashtagsAndMentionsInTweet(
    contentOfTweet: String,
    entity: Entity,
    navController: NavController
) {
    val hashtagsList = entity.hashtags
    val mentionsList = entity.mentions
    val urlList = entity.urls
    with((AnnotatedString.Builder())) {
        append(contentOfTweet)
        hashtagsList?.forEach { hashtagsListItem ->
            addStringAnnotation(
                tag = hashtagsListItem.tag,
                start = hashtagsListItem.start,
                end = hashtagsListItem.end,
                annotation = hashtagsListItem.tag
            )
        }
        mentionsList?.forEach { mentionListItem ->
            addStringAnnotation(
                tag = mentionListItem.username,
                start = mentionListItem.start,
                end = mentionListItem.end,
                annotation = mentionListItem.username
            )
        }
        urlList?.forEach { urlListItem ->
            addStringAnnotation(
                tag = urlListItem.url,
                start = urlListItem.start,
                end = urlListItem.end,
                annotation = urlListItem.displayUrl
            )
        }
    }
}


