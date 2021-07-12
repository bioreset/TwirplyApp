package com.dariusz.twirplyapp.presentation.components.search

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.dariusz.twirplyapp.presentation.components.navigation.Screens
import com.dariusz.twirplyapp.utils.NavigationUtils.navigateToWithArgument

@ExperimentalComposeUiApi
@Composable
fun SearchBox(
    navController: NavController
) {
    val searchQuery = remember(TextFieldValue) { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        value = searchQuery.value,
        onValueChange = { it ->
            searchQuery.value = it
        },
        label = { Text("Search") },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Search,
        ),
        leadingIcon = { Icons.Filled.Search },
        keyboardActions = KeyboardActions(
            onSearch = {
                keyboardController?.hide()
                navigateToWithArgument(
                    navController,
                    Screens.AppScreens.SearchResults.route,
                    searchQuery.value
                )
            }
        ),
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth(.9f)
            .padding(10.dp)
    )
}



