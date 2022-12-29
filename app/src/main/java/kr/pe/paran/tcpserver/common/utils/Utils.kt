package kr.pe.paran.tcpserver.common.utils

import java.text.SimpleDateFormat
import java.util.*

object Utils {

    fun getTimeStamp(): String {
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.KOREAN)
        return simpleDateFormat.format(Date())
    }
}