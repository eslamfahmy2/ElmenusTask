package com.fahmy.task.presentation.navigation

sealed class Screens(val route: String) {

    object Menu : Screens("menu")

    object ITemDetails : Screens("details/{item}")
}
