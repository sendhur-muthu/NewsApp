package com.sendhur.newsapp.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen: Screen("home")
}
