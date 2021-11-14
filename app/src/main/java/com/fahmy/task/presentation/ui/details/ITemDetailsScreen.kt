package com.fahmy.task.presentation.ui.details

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBackIos
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import com.fahmy.task.domian.model.Item
import java.lang.Float.min

@RequiresApi(Build.VERSION_CODES.N)
@Composable
fun ITemDetailsScreen(
    item: Item,
    navHostController: NavHostController
) {

    val scrollState = rememberScrollState()
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp

    ConstraintLayout {

        val (header) = createRefs()

        Image(
            painter = rememberImagePainter(item.photoUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .constrainAs(header) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .fillMaxWidth()
                .height(400.dp)
                .graphicsLayer {
                    alpha = min(1f, 1 - (scrollState.value / 1000f))
                    translationY = -scrollState.value * 0.8f
                }

        )


        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            item.description?.let {
                Text(
                    text = it,
                    modifier = Modifier
                        .padding(top = 400.dp)
                        .fillMaxSize()
                        .background(MaterialTheme.colors.surface)
                        .padding(8.dp)
                    ,
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface,

                    )
                )
            }
            val policy = MeasurePolicy { _, constraints ->
                layout(constraints.maxWidth, constraints.maxHeight) {}
            }
            val modifier  = Modifier
                .height(screenHeight.dp)
                .background(MaterialTheme.colors.surface)

            Layout({}, measurePolicy = policy, modifier = modifier)


        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .alpha((scrollState.value / 1000f))
                .background(MaterialTheme.colors.primary),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            IconButton(
                onClick = {
                    navHostController.popBackStack()
                },
            ) {
                Icon(Icons.Rounded.ArrowBackIos, contentDescription = "Localized description")
            }

            item.name?.let {
                Text(
                    text = it,
                    modifier = Modifier.padding(12.dp),
                    style = TextStyle(
                        color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

        }


    }
}