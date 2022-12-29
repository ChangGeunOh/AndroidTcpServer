package kr.pe.paran.tcpserver

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import kotlinx.serialization.ExperimentalSerializationApi
import kr.pe.paran.tcpserver.screens.MainScreen
import kr.pe.paran.tcpserver.ui.theme.TcpServerTheme

class MainActivity : ComponentActivity() {

    private val viewModel : MainViewModel  by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            TcpServerTheme {
                MainScreen(viewModel = viewModel)
            }
        }

    }
}