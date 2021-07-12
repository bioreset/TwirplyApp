package com.dariusz.twirplyapp.presentation.components.profile

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.dariusz.twirplyapp.domain.model.Errors
import com.dariusz.twirplyapp.domain.model.GenericResponse
import com.dariusz.twirplyapp.domain.model.Includes
import com.dariusz.twirplyapp.domain.model.User
import com.google.accompanist.coil.rememberCoilPainter

@Composable
fun ProfileFeed(
    user: GenericResponse<User?, Includes?, Errors?, Nothing>,
    navController: NavController
) {
    val userInfoFull = user.outputOne
    Column {
        Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp)) {
            Spacer(modifier = Modifier.height(44.dp))
            if (userInfoFull != null) {
                Avatar(user = userInfoFull)
                UserInfo(user = userInfoFull)
                FollowButton()
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
        PinnedTweet(user, navController)
    }

}

@Composable
fun UserInfo(user: User) {
    Text(
        text = user.name,
        style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp)
    )
    Text(text = user.username)
    Spacer(modifier = Modifier.height(8.dp))
    Text(
        text = user.description,
        style = TextStyle(fontSize = 14.sp)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${user.publicMetrics.followingCount}",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Following",
            style = TextStyle(fontSize = 14.sp)
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = "${user.publicMetrics.followersCount}",
            style = TextStyle(fontWeight = FontWeight.Bold)
        )
        Text(
            text = "Followers",
            style = TextStyle(fontSize = 14.sp)
        )
    }
}

@Composable
private fun Avatar(user: User) {
    Image(
        painter = rememberCoilPainter(
            request = user.profileImageUrl
        ),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .clip(shape = RoundedCornerShape(40.dp))
            .border(
                border = BorderStroke(width = 4.dp, color = MaterialTheme.colors.surface),
                shape = RoundedCornerShape(40.dp)
            ),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun FollowButton() {
    Button(
        onClick = {
            //TODO FOLLOW ACTION
        },
        shape = RoundedCornerShape(20.dp),
        border = BorderStroke(1.dp, MaterialTheme.colors.primary)
    ) {
        Text(text = "Follow")
    }
}
