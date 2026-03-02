package br.com.androidtest.navigation

sealed class AppDestination(val route: String) {
    data object Main : AppDestination("main")
    data object MyData : AppDestination("my_data/{platform}") {
        fun routeWithPlatform(platform: String): String = "my_data/$platform"
    }

    data object MyPlan : AppDestination("my_plan/{platform}") {
        fun routeWithPlatform(platform: String): String = "my_plan/$platform"
    }
}
