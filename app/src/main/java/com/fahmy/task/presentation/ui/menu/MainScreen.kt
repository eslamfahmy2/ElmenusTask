package com.fahmy.task.presentation.ui.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.fahmy.task.R
import com.fahmy.task.domian.utils.DataState
import com.fahmy.task.presentation.ui.menu.component.*
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@ExperimentalMaterialApi
@Composable
fun MainScreen(
    menuViewModel: MenuViewModel = hiltViewModel(),
    navHostController: NavHostController,
    toggleTheme: () -> Unit
) {

    val coroutineScope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()
    val tags = menuViewModel.tags.collectAsState().value.collectAsLazyPagingItems()
    val items = menuViewModel.items.collectAsState().value

    Scaffold(
        topBar = {
            AppBar(title = stringResource(id = R.string.app_name)) {
                toggleTheme()
            }
        },
        scaffoldState = scaffoldState,
        snackbarHost = {
            scaffoldState.snackbarHostState
        },
        bottomBar = {
            SnackBar(
                snackHostState = scaffoldState.snackbarHostState,
                onDismiss = {
                    scaffoldState.snackbarHostState.currentSnackbarData?.dismiss()
                },
            )
        }
    ) {


        Column {

            DataScreen(tags = tags, action = {
                coroutineScope.launch {
                    menuViewModel.userIntent.send(MenuIntent.LoadItemsByTagName(it))
                }
            })

            when (items) {
                is DataState.Error -> {
                    items.message?.let {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar(
                                message = it,
                                actionLabel = "Dismiss",
                            )
                        }
                    }
                }
                is DataState.Loading -> {
                    LoadingColumnScreen(count = 5, height = 200.dp)
                }
                is DataState.Success -> {
                    items.data?.let {
                        LazyColumn() {
                            items(it) { item ->
                                ITemCard(item = item, action = {
                                    val encodedUrl = URLEncoder.encode(
                                        it.photoUrl,
                                        StandardCharsets.UTF_8.toString()
                                    )
                                    val encodedItem = it.copy(photoUrl = encodedUrl)
                                    Gson().toJson(encodedItem)?.let { json ->
                                        navHostController.navigate("details/${json}")
                                    }
                                })
                            }
                        }
                    }

                }
            }


        }

    }

    LaunchedEffect(true) {
        menuViewModel.userIntent.send(MenuIntent.LoadTags)
    }
}