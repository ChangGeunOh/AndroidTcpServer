package kr.pe.paran.tcpserver.navigation

sealed class Screen(val route: String) {
    object MainScreen : Screen("main_screen")
    object SettingScreen : Screen("setting_screen")
    object QrCodeScreen : Screen("qrcode_screen/{qrCode}") {
        fun passQrCode(qrcode: String): String = "qrcode_screen/$qrcode"
    }
}
