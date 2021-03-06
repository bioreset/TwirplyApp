package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.dariusz.twirplyapp.domain.model.UserMinimum
import com.dariusz.twirplyapp.presentation.components.theme.ThemeTypography

@ExperimentalCoilApi
@Composable
fun CompactUserInfoList(
    title: String,
    input: List<UserMinimum>,
    actionOpenProfile: (String) -> Unit
) {
    LazyColumn {
        item {
            TitleText(title)
        }
        items(input) { item ->
            CompactUserInfo(item, actionOpenProfile)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun CompactUserInfo(
    input: UserMinimum,
    actionOpenProfile: (String) -> Unit
) {
    Row(
        modifier = Modifier.clickable(onClick = {
            actionOpenProfile.invoke(input.id)
        })
    ) {
        AuthorPicture(input)
        Column(
            modifier = Modifier
                .padding(top = 4.dp, bottom = 4.dp)
                .fillMaxWidth()
        ) {
            AuthorInfo(input)
        }
    }
}

@ExperimentalCoilApi
@Composable
fun AuthorPicture(userInfo: UserMinimum) {
    userInfo.profileImageUrl?.let {
        DisplayImage(
            url = it,
            modifier = Modifier
                .size(50.dp)
                .clip(shape = RoundedCornerShape(45.dp))
                .border(
                    border = BorderStroke(width = 4.dp, color = MaterialTheme.colors.surface),
                    shape = RoundedCornerShape(45.dp)
                )
        )
    }
}

@Composable
fun AuthorInfo(userInfo: UserMinimum) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = userInfo.name,
            style = MaterialTheme.typography.h3,
            modifier = Modifier.padding(start = 8.dp, end = 4.dp)
        )

        if (userInfo.isVerified == true) Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFF1DA1F2),
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically)
                .padding(top = 2.dp)
        )
    }
    Row {
        Text(
            text = " @" + userInfo.username,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun TitleText(
    text: String
) = Text(text, style = ThemeTypography.h2, modifier = Modifier.padding(4.dp))