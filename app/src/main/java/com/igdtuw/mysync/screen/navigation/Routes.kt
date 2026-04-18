package com.igdtuw.mysync.screen.navigation

import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer

sealed class Routes(val route: String) {

    data object Dash_main : Routes("dash_main")

    data object Dash_CR : Routes("dash_cr")

}