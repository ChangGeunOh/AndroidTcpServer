package kr.pe.paran.tcpserver.screen.qrcode

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QrCodeScreen(
    navController: NavController,
    viewModel: QrCodeViewModel = viewModel()
) {
    val qrCode by viewModel.qrCode.collectAsState()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            TopAppBar(
                modifier = Modifier.fillMaxWidth(),
                title = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "QR Code",
                        textAlign = TextAlign.Center
                    )
                },
//                navigationIcon = {
//                    IconButton(
//                        onClick = { navController.popBackStack() }
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.ArrowBack,
//                            contentDescription = "ArrowBack Icon"
//                        )
//                    }
//                },
            )
        },
    ) {
        QrCodeContent(
            qrCode = qrCode,
            onClickClose = { navController.popBackStack() }
        )
    }
}