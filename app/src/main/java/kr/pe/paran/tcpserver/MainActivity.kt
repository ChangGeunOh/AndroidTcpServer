package kr.pe.paran.tcpserver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.compose.rememberNavController
import kr.pe.paran.tcpserver.navigation.SetupNavGraph
import kr.pe.paran.tcpserver.screen.main.MainViewModel
import kr.pe.paran.tcpserver.ui.theme.TcpServerTheme

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {

            val navController = rememberNavController()

            TcpServerTheme {
                SetupNavGraph(navHostController = navController)
            }
        }

    }
}