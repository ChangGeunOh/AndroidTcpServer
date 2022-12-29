package kr.pe.paran.tcpserver

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import kr.pe.paran.tcpserver.common.utils.NetworkUtils
import kr.pe.paran.tcpserver.model.LogData
import kr.pe.paran.tcpserver.model.ServerState
import kr.pe.paran.tcpserver.plugins.Router
import java.util.*

class MainViewModel: ViewModel() {

    private var router: Router? = null
    private val assetInstaller: AssetInstaller = AssetInstaller(this)

    private var _publicAddress = MutableStateFlow("")
    val publicAddress = _publicAddress.asStateFlow()

    private var _deviceAddress = MutableStateFlow("")
    val deviceAddress = _deviceAddress.asStateFlow()


    private val _serverState = MutableStateFlow(ServerState.STOP)
    val serverState = _serverState.asStateFlow()


    private var _logDataList = MutableStateFlow<List<LogData>>(emptyList())
    val logDataList = _logDataList.asStateFlow()

    fun getAddress() {
        _deviceAddress.value = NetworkUtils.getDeviceAddress()
        viewModelScope.launch(Dispatchers.IO) {
            _publicAddress.value = NetworkUtils.getPublicAddress()
        }
    }

    fun start(context: Context) {
        val staticFileDirectory = context.filesDir.absolutePath
        if (router == null) {
            _serverState.value = ServerState.INIT
            router = Router(context = context)

            viewModelScope.launch {
                assetInstaller.install(
                    context = context,
                    sourceDirectory = "",
                    targetDirectory =  staticFileDirectory
                )
            }
        }
        router!!.startServer(staticFileDirectory, "")
        _serverState.value = ServerState.RUN
        log(LogData(message = "Server Run"))
    }

    fun stop() {
        router?.stopServer()
        router = null
        _serverState.value = ServerState.STOP
        log(LogData(message = "Server Stop"))
    }

    fun log(logData: LogData) {
        _logDataList.value += logData
    }
}