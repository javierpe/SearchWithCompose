package com.app.ml.api.controllers

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptionsBuilder
import com.app.ml.data.models.Route

interface NavigationController {

    @Composable
    fun Configure(builder: NavGraphBuilder.(NavHostController) -> Unit)

    fun navigateTo(
        route: Route,
        args: List<String>? = null,
        builder: (NavOptionsBuilder.() -> Unit)? = null
    )

    fun navigateTo(
        route: String,
        args: List<String>? = null,
        builder: (NavOptionsBuilder.() -> Unit)? = null
    )

    fun popBackStack()
}