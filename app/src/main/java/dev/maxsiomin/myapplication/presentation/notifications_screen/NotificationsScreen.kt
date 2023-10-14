package dev.maxsiomin.myapplication.presentation.notifications_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import dev.maxsiomin.myapplication.domain.model.SavedNotification
import dev.maxsiomin.myapplication.ui.theme.NotificationReaderTheme

@Composable
fun NotificationsScreen(
    navController: NavController,
    viewModel: NotificationsViewModel = hiltViewModel(),
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    BackHandler(enabled = viewModel.state.searchBoxExpanded) {
        viewModel.onEvent(NotificationsViewModel.Event.CloseSearch)
    }

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collect { event ->
            when (event) {
                is NotificationsViewModel.UiEvent.ClearFocus -> focusManager.clearFocus()
                is NotificationsViewModel.UiEvent.Navigate -> event.navigate(navController)
            }
        }
    }

    NotificationsScreenScaffold(
        navController = navController,
        state = viewModel.state,
        focusRequester = focusRequester,
        onEvent = viewModel::onEvent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsScreenScaffold(
    navController: NavController,
    state: NotificationsScreenState,
    focusRequester: FocusRequester,
    onEvent: (NotificationsViewModel.Event) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            if (state.searchBoxExpanded) {
                SearchAppBar(state = state, focusRequester = focusRequester, onEvent = onEvent)
            } else {
                DefaultTopAppBar(navController = navController, onEvent = onEvent)
            }
        }
    ) { values ->
        LazyColumn(
            Modifier
                .fillMaxSize()
                .padding(values)
        ) {
            items(state.notificationsList.size) { index ->
                NotificationItem(savedNotification = state.notificationsList.elementAt(index))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DefaultTopAppBar(
    navController: NavController,
    onEvent: (NotificationsViewModel.Event) -> Unit
) {
    TopAppBar(
        title = {
            Text(text = "All notifications")
        },
        navigationIcon = {
            IconButton(onClick = {
                onEvent(NotificationsViewModel.Event.GoBack(navController))
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go back"
                )
            }
        },
        actions = {
            IconButton(onClick = {
                onEvent(NotificationsViewModel.Event.OpenSearch)
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search"
                )
            }
            IconButton(onClick = {
                onEvent(NotificationsViewModel.Event.Refresh)
            }) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = "Refresh"
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
private fun SearchAppBar(
    state: NotificationsScreenState,
    focusRequester: FocusRequester,
    onEvent: (NotificationsViewModel.Event) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        //elevation = AppBarDefaults.TopAppBarElevation,
        //color = MaterialTheme.colors.primary
    ) {
        val keyboardController = LocalSoftwareKeyboardController.current

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester)
                .onFocusChanged {
                    if (it.isFocused) {
                        keyboardController?.show()
                    }
                },
            value = state.searchBoxContent,
            onValueChange = {
                onEvent(NotificationsViewModel.Event.SearchBoxTextChanged(it))
            },
            leadingIcon = {
                IconButton(
                    onClick = {
                        onEvent(NotificationsViewModel.Event.CloseSearch)
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Hide search box",
                    )
                }
            },
            placeholder = {
                Text(
                    modifier = Modifier,
                    text = "Search here...",
                )
            },
            textStyle = TextStyle(
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            ),
            singleLine = true,
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (state.searchBoxContent.isNotEmpty()) {
                            onEvent(NotificationsViewModel.Event.SearchBoxTextChanged(""))
                        } else {
                            onEvent(NotificationsViewModel.Event.CloseSearch)
                        }
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon",
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done
            ),
        )

    }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
fun NotificationsScreenPreview() {
    NotificationReaderTheme {
        NotificationsScreenScaffold(
            rememberNavController(),
            state = NotificationsScreenState(
                notificationsList = listOf(
                    SavedNotification(
                        app = "org.telegram.messenger",
                        title = "Misha",
                        text = "Hello Max",
                        time = 1691099489L,
                    ),
                    SavedNotification(
                        app = "org.telegram.messenger",
                        title = "Misha",
                        text = "Hello Max",
                        time = 1691099489L,
                    ),
                ),
                true
            ),
            FocusRequester(),
            {}
        )
    }
}
