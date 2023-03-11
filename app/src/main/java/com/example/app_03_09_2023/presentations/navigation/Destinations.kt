package com.example.app_03_09_2023.presentations.navigation

sealed class Destinations(
    val route : String
) {
    object LoginScreen : Destinations(route = "LoginScreen/{nameValue}") {
        fun crearRouteNueva(nameValue : String) : String {
            return "LoginScreen/$nameValue"
        }
    }
    object AddScreen : Destinations(route = "AddScreen")
    object RegistrationScreen : Destinations(route = "RegistrationScreen")
    object ForgotPassword : Destinations(route = "ForgotPassword")
    object HomeScreen : Destinations("HomeScreen/{user}") {
        fun createRoute(user: String): String {
            return "HomeScreen/$user"
        }
    }
}
