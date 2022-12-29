package kr.pe.paran.tcpserver.model

import kr.pe.paran.tcpserver.common.utils.Utils

@kotlinx.serialization.Serializable
data class LogData(
    var timeStamp: String = Utils.getTimeStamp(),
    var message: String
) {
    fun getLog() = "$timeStamp : $message"
}