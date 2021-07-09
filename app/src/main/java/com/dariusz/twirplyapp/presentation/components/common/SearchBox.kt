package com.dariusz.twirplyapp.presentation.components.common

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@Composable
fun SearchBox(
    navController: NavController
){
    val searchQuery = remember (TextFieldValue) { mutableStateOf("") }
    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = { it ->
            searchQuery.value = it
        },
        label = { "Search" },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done,
        ),
        leadingIcon = { Icons.Filled.Search },
        onImeActionPerformed = { imeaction, softKeyboardController ->
            if (imeaction == ImeAction.Done) {
                softKeyboardController.hideSoftwareKeyboard()
                navigateToWithArgument(
                    navController,
                    Screens.AppScreens.SearchResults.route,
                    searchQuery.value
                )
            }
        },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(10.dp)
    )
}


