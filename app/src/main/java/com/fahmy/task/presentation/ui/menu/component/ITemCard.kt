package com.fahmy.task.presentation.ui.menu.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.fahmy.task.domian.model.Item


@ExperimentalMaterialApi
@Composable
fun ITemCard(
    item: Item,
    action: (Item) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        onClick = { action(item) },
        shape = MaterialTheme.shapes.small,
        elevation = 8.dp

    ) {

        Column {

            Image(
                painter = rememberImagePainter(item.photoUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp)
            )

            item.name?.let {

                Text(
                    text = it,
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize(),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    ),
                )

            }
            item.description?.let {

                Text(
                    text = it,
                    modifier = Modifier
                        .padding(8.dp)
                        .wrapContentSize(),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface
                    ),
                )

            }

        }

    }
}