package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi

private const val linkToImage =
    "https://cdn.cms-twdigitalassets.com/content/dam/developer-twitter/auth-docs/sign-in-with-twitter-link.png.twimg.1920.png"

@Composable
fun LogInScreen(
    logInAction: () -> Unit?,
    getIntoAppWithoutLoggingIn: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        content = {
            Text(text = "Hello")
            Spacer(modifier = Modifier.size(10.dp))
            SignInWithTwitterButton(
                action = {
                    logInAction.invoke()
                }
            )
            Spacer(modifier = Modifier.size(10.dp))
            Button(
                onClick = {
                    getIntoAppWithoutLoggingIn.invoke()
                }
            ) {
                Text("Use app without login")
            }
        }

    )

}

@ExperimentalCoilApi
@Composable
fun SignInWithTwitterButton(action: @Composable () -> Unit) {
    val buttonClicked = remember { mutableStateOf(false) }
    DisplayImage(
        url = linkToImage,
        modifier = Modifier
            .clickable(onClick = {
                buttonClicked.value = true

            })
            .size(130.dp)
    )
    if (buttonClicked.value) action.invoke()
}