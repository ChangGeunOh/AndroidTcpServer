package kr.pe.paran.tcpserver.navigation.navtype

import android.os.Bundle
import androidx.navigation.NavType
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import kr.pe.paran.tcpserver.model.IpAddressData

class IpAddressType: NavType<IpAddressData>(isNullableAllowed = false) {

    @Suppress("DEPRECATION")
    override fun get(bundle: Bundle, key: String): IpAddressData? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): IpAddressData {
        return Json.decodeFromString(value)
    }

    override fun put(bundle: Bundle, key: String, value: IpAddressData) {
        bundle.putParcelable(key, value)
    }
}