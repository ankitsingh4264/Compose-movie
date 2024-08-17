package com.example.featuresapp.ui.screens

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavHostController


val LocalAppNavController = staticCompositionLocalOf<NavHostController> {
    error("No NavController provided")
}

enum class Screens( val value: String) {
    HOME("home"),
    DETAILS("details")
}