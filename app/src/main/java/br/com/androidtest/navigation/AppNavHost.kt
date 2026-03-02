package br.com.androidtest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.androidtest.common.PlatformType
import br.com.androidtest.features.main.view.MainScreen
import br.com.androidtest.features.mydata.view.MyDataScreen
import br.com.androidtest.features.myplan.view.MyPlanScreen

@Composable
fun AppNavHost(onExitApp: () -> Unit) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppDestination.Main.route
    ) {
        composable(AppDestination.Main.route) {
            MainScreen(
                onOpenPlatform = { platform ->
                    navController.navigate(AppDestination.MyData.routeWithPlatform(platform.routeValue))
                },
                onExitApp = onExitApp
            )
        }

        composable(
            route = AppDestination.MyData.route,
            arguments = listOf(navArgument("platform") { type = NavType.StringType })
        ) { entry ->
            val platform = PlatformType.fromRoute(entry.arguments?.getString("platform"))
            MyDataScreen(
                platformType = platform,
                onBack = { navController.popBackStack() },
                onOpenMyPlan = {
                    navController.navigate(AppDestination.MyPlan.routeWithPlatform(platform.routeValue))
                },
                onExitApp = onExitApp
            )
        }

        composable(
            route = AppDestination.MyPlan.route,
            arguments = listOf(navArgument("platform") { type = NavType.StringType })
        ) { entry ->
            val platform = PlatformType.fromRoute(entry.arguments?.getString("platform"))
            MyPlanScreen(
                platformType = platform,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
