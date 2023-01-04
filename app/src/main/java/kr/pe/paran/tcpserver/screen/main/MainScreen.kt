package kr.pe.paran.tcpserver.screen.main

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.QrCode
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kr.pe.paran.tcpserver.model.ServerState
import kr.pe.paran.tcpserver.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {


    val context = LocalContext.current
    val publicAddress by viewModel.publicAddress.collectAsState()
    val deviceAddress by viewModel.deviceAddress.collectAsState()

    val serverState by viewModel.serverState.collectAsState()
    val logDataList by viewModel.logDataList.collectAsState()

    val listState = rememberLazyListState()

    LaunchedEffect(key1 = Unit, block = { viewModel.getAddress() })

    LaunchedEffect(key1 = logDataList, block = {
        if (logDataList.isNotEmpty()) {
            listState.animateScrollToItem(logDataList.lastIndex)
        }
    })

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF121212)),
        topBar = {
            TopAppBar(
                modifier = Modifier
                    .fillMaxWidth(),
                title = {
                    Text("Android TCP Server")
                },
                actions = {
                    IconButton(
                        onClick = {
                            viewModel.onSavedSateHandle()
                            navController.navigate("setting_screen/fiveroot")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Settings Icon"
                        )
                    }
                    IconButton(
                        onClick = {
                            navController.navigate(Screen.QrCodeScreen.passQrCode("publicAddress"))
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.QrCode,
                            contentDescription = "Settings Icon"
                        )
                    }
                }
            )
        }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(vertical = 8.dp, horizontal = 16.dp),
        ) {

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
            LazyColumn(
                state = listState,
                modifier = Modifier.weight(1f)
            ) {
                items(items = logDataList) {
                    Text(
                        text = it.getLog(),
                        fontSize = 12.sp
                    )
                }
            }
            Text(
                modifier = Modifier.padding(top = 8.dp),
                text = "STATE : ${serverState.message}",
                fontSize = 14.sp
            )
        }
    }
}