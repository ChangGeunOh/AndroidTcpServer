package kr.pe.paran.tcpserver.screen.qrcode

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kr.pe.paran.tcpserver.common.Constants

class QrCodeViewModel(savedStateHandle: SavedStateHandle) : ViewModel() {

    private var _qrCode = MutableStateFlow<String?>(null)
    val qrCode = _qrCode.asStateFlow()

    init {
        _qrCode.value = savedStateHandle[Constants.QR_CODE]
    }
}