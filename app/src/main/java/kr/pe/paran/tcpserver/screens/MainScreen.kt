package kr.pe.paran.tcpserver.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.pe.paran.tcpserver.MainViewModel
import kr.pe.paran.tcpserver.model.ServerState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(viewModel: MainViewModel) {

    val context = LocalContext.current
    val publicAddress by viewModel.publicAddress.collectAsState()
    val deviceAddress by viewModel.deviceAddress.collectAsState()
    val serverState by viewModel.serverState.collectAsState()
    val logDataList by viewModel.logDataList.collectAsState()

    LaunchedEffect(key1 = Unit, block = { viewModel.getAddress() })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212))
            .padding(vertical = 8.dp, horizontal = 16.dp)

    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            Text(
                text = "Public IP : $publicAddress",
                fontSize = 14.sp
            )
            Text(
                text = "Device IP : $deviceAddress",
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (serverState == ServerState.RUN) {
                        viewModel.stop()
                    } else {
                        viewModel.start(context = context)
                    }
                }) {
                Text(text = if (serverState == ServerState.RUN) "STOP" else "START")
            }
            Spacer(modifier = Modifier.height(16.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                items(items = logDataList) {
                    Text(
                        text = it.getLog(),
                        fontSize = 12.sp
                    )
                }
            }
            Text(
                text = "STATE : ${serverState.message}",
                fontSize = 14.sp
            )
        }
    }
}