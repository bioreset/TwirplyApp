package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.LocalImageLoader
import coil.compose.rememberImagePainter
import com.dariusz.twirplyapp.domain.model.UserMinimum

@Composable
fun CompactUserInfoList(
    input: List<UserMinimum>,
    actionOpenProfile: (String) -> Unit
) {
    LazyColumn {
        items(input) { item ->
            CompactUserInfo(item, actionOpenProfile)
        }
    }
}

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
                .padding(8.dp)
                .fillMaxWidth()
        ) {
            AuthorInfo(input)
        }
    }
}


@Composable
fun AuthorPicture(userInfo: UserMinimum) {
    Image(
        painter = rememberImagePainter(
            data = userInfo.profileImageUrl,
            imageLoader = LocalImageLoader.current
        ),
        contentDescription = userInfo.username + "'s profile picture",
    )
}

@Composable
fun AuthorInfo(userInfo: UserMinimum) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = userInfo.name,
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(end = 4.dp)
        )

        if (userInfo.isVerified) Icon(
            imageVector = Icons.Default.CheckCircle,
            contentDescription = null,
            tint = Color(0xFF1DA1F2),
            modifier = Modifier
                .size(18.dp)
                .align(Alignment.CenterVertically)
                .padding(top = 2.dp)
        )

        Text(
            text = userInfo.username,
            modifier = Modifier.padding(start = 8.dp),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Center
        )
    }
}
