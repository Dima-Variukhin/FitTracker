package com.my.fittracker.app

import kotlinx.serialization.Serializable

sealed interface Route {

    @Serializable
    data object LoginGraph: Route
    data object Login: Route
}