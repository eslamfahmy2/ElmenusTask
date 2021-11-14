package com.fahmy.task.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import com.fahmy.task.presentation.navigation.MainNavigation
import com.fahmy.task.presentation.theme.NewsTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@ExperimentalCoroutinesApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalFoundationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var baseApplication: BaseApplication

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NewsTheme(darkTheme = baseApplication.isDark()) {
                MainNavigation {
                    baseApplication.toggleTheme()
                }
            }
        }

    }

}