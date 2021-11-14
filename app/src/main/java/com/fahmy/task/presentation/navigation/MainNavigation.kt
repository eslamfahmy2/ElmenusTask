package com.fahmy.task.presentation.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.fahmy.task.domian.model.Item
import com.fahmy.task.presentation.ui.details.ITemDetailsScreen
import com.fahmy.task.presentation.ui.menu.MainScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.gson.Gson
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RequiresApi(Build.VERSION_CODES.N)
@ExperimentalCoroutinesApi
@ExperimentalFoundationApi
@ExperimentalAnimationApi
@ExperimentalMaterialApi
@Composable
fun MainNavigation(toggleTheme: () -> Unit) {

    val navHostController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navHostController, startDestination = Screens.Menu.route) {

        composable(route = Screens.Menu.route) {
            MainScreen(navHostController = navHostController, toggleTheme = toggleTheme)
        }
        composable(
            route = Screens.ITemDetails.route,
            arguments = listOf(navArgument("item") { type = NavType.StringType })
        ) { backStackEntry ->
            backStackEntry.arguments?.getString("item")?.let {
                Gson().fromJson(it, Item::class.java)
                    ?.let { item ->
                        ITemDetailsScreen(
                            item = item,
                            navHostController = navHostController
                        )
                    }
            }
        }

    }

}