package kr.pe.paran.tcpserver.screen.setting

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingViewModel(savedStateHandle: SavedStateHandle): ViewModel() {

    private var _value = MutableStateFlow<String>("")
    val value = _value.asStateFlow()

    init {
        Log.i(":::::SettingViewModel", "init....")
        val parameter = savedStateHandle.get<String>("PARAMETER")
        Log.i(":::::SettingViewModel", savedStateHandle.toString())
        Log.i(":::::SettingViewModel", "value>${parameter}")
    }
}