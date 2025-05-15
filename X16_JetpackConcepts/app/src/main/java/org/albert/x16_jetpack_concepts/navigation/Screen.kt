package org.albert.x16_jetpack_concepts.navigation

const val DETAILS_REQUIRED_ARG_KEY = "id"
const val DETAILS_OPTIONAL_ARG_KEY = "name"

const val ROOT_ROUTE = "root"
const val HOME_ROUTE = "home"
const val AUTH_ROUTE = "auth"

sealed class Screen(val route: String) {
    data object Home : Screen("home_screen")
    data object Details : Screen(
        route = "details_screen/{$DETAILS_REQUIRED_ARG_KEY}?name={$DETAILS_OPTIONAL_ARG_KEY}"
    ) {
        fun withId(id: Int): String {
            return this.route.replace(oldValue = DETAILS_REQUIRED_ARG_KEY, newValue = id.toString())
        }
        fun withIdAndName(id: Int, name: String = "default"): String {
            return "details_screen/${id}?name=${name}"
        }
    }
    data object Login : Screen("login_screen")
    data object SignUp : Screen("signup_screen")
}
