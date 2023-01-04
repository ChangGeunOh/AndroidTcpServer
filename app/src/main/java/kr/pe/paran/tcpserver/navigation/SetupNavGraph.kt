package kr.pe.paran.tcpserver.navigation

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import kr.pe.paran.tcpserver.common.Constants
import kr.pe.paran.tcpserver.screen.main.MainScreen
import kr.pe.paran.tcpserver.screen.qrcode.QrCodeScreen
import kr.pe.paran.tcpserver.screen.setting.SettingScreen

@Composable
fun SetupNavGraph(
    navHostController: NavHostController
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.MainScreen.route
    ) {
        composable(route = Screen.MainScreen.route) {
            val parameter = it.savedStateHandle.get<String>("PARAMETER")
            Log.i(":::::MainNav", "MainScreen value>${parameter}")
            Log.i(":::::MainNav", it.savedStateHandle.toString())
            MainScreen(navController = navHostController)
        }

        composable(
            route = Screen.QrCodeScreen.route,
            arguments = listOf(
                navArgument(
                    name = Constants.QR_CODE
                ) { type = NavType.StringType }
            )
        ) {
            QrCodeScreen(navController = navHostController)
        }

        composable(
            route = "setting_screen/{PARAMETER}",
            arguments = listOf(navArgument("PARAMETER") {
                type = NavType.StringType
            })
        ) {
            navHostController.previousBackStackEntry?.savedStateHandle.toString()
            val parameter = it.savedStateHandle.get<String>("PARAMETER")

            Log.i(":::::SettingNav", "Setting value>${parameter}")
            Log.i(":::::SettingNav", it.savedStateHandle.toString())
            Log.i(
                ":::::SettingNav",
                navHostController.previousBackStackEntry?.savedStateHandle.toString()
            )
            Log.i(
                ":::::SettingNav",
                navHostController.currentBackStackEntry?.savedStateHandle.toString()
            )
            it.savedStateHandle["PARAMETER"] = "test....."
            SettingScreen(navController = navHostController)
        }
    }
}