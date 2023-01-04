package kr.pe.paran.tcpserver.screen.setting

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingScreen(
    navController: NavController,
    viewModel: SettingViewModel = viewModel()
) {
    Scaffold(modifier = Modifier.fillMaxSize()) {
        Text("Setting Screen....")
    }
}