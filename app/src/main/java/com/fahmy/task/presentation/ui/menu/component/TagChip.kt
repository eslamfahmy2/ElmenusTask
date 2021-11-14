package com.fahmy.task.presentation.ui.menu.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fahmy.task.domian.model.Tag


@Composable
fun TagChip(
    tag: Tag,
    action: (Tag) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .clickable {
                action(tag)
            },
        elevation = 5.dp,
        shape = MaterialTheme.shapes.small,
        backgroundColor = MaterialTheme.colors.primary

    ) {

        Text(
            text = tag.tagName,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            modifier = Modifier.padding(
                start = 10.dp,
                end = 10.dp,
                top = 8.dp,
                bottom = 8.dp
            )
        )
    }

}
