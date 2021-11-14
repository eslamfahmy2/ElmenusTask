package com.fahmy.task.presentation.ui.menu.component

import androidx.compose.foundation.background
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.items
import com.fahmy.task.domian.model.Tag


@ExperimentalMaterialApi
@Composable
fun DataScreen(
    tags: LazyPagingItems<Tag>,
    action: (Tag) -> Unit
) {

    if (tags.loadState.refresh == LoadState.Loading) {
        LoadingRowScreen(
            count = 5,
            height = 60.dp
        )
    } else {

        LazyRow(Modifier.background(MaterialTheme.colors.surface)) {
            items(tags) {
                it?.let {
                    TagChip(tag = it, action = { action(it) })
                }
            }

            if (tags.loadState.prepend == LoadState.Loading) {
                item {
                    LoadingRowScreen(count = 1, height = 60.dp)
                }
            }

        }

    }


}



