package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.buildAnnotatedString
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.Entity
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument
import com.dariusz.twirplyapp.utils.NavigationUtils.openInBrowser

@Composable
fun buildFullString(
    input: String,
    entity: Entity,
    navController: NavController
): AnnotatedString {

    val currentContext = LocalContext.current

    val hashtagsList = entity.hashtags
    val mentionsList = entity.mentions
    val urlList = entity.urls

    return buildAnnotatedString {
        append(input)
        hashtagsList?.forEach { hashtagsListItem ->
            ClickableStringAnnotation(
                input = input,
                tag = hashtagsListItem.tag,
                start = hashtagsListItem.start,
                end = hashtagsListItem.end,
                annotation = hashtagsListItem.tag,
                action = {
                    navigateToWithArgument(
                        navController,
                        Screens.AppScreens.SearchResults.route,
                        hashtagsListItem.tag
                    )
                }
            )
        }
        mentionsList?.forEach { mentionListItem ->
            ClickableStringAnnotation(
                input = input,
                tag = mentionListItem.username,
                start = mentionListItem.start,
                end = mentionListItem.end,
                annotation = mentionListItem.username,
                action = {
                    navigateToWithArgument(
                        navController,
                        Screens.AppScreens.ProfileScreen.route,
                        mentionListItem.username
                    )
                }
            )
        }
        urlList?.forEach { urlListItem ->
            ClickableStringAnnotation(
                input = input,
                tag = urlListItem.url,
                start = urlListItem.start,
                end = urlListItem.end,
                annotation = urlListItem.displayUrl,
                action = {
                    openInBrowser(
                        currentContext,
                        urlListItem.fullUrl
                    )
                }
            )
        }
    }
}

@Composable
fun ClickableStringAnnotation(
    input: String,
    tag: String,
    start: Int,
    end: Int,
    annotation: String,
    action: () -> Unit
) {

    val annotatedString = buildAnnotatedString {
        append(input.substring(start until end))
        addStringAnnotation(
            tag = tag,
            start = start,
            end = end,
            annotation = annotation
        )
    }

    ClickableText(
        text = annotatedString,
        onClick = {
            action.invoke()
        },
        style = ThemeTypography.body1.copy(Color(0xFF1DA1F2))
    )

}