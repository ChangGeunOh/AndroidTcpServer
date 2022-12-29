package kr.pe.paran.tcpserver.common.utils

import kr.pe.paran.tcpserver.common.Constants
import okhttp3.ResponseBody
import retrofit2.*
import retrofit2.http.GET
import java.net.NetworkInterface
import java.util.*


interface RetrofitService {
    @GET("/")
    fun getUserIP(): Call<ResponseBody>
}


object NetworkUtils {

    fun getDeviceAddress(): String {
        var deviceIPAddress = ""
        val interfaces = Collections.list(NetworkInterface.getNetworkInterfaces())
        interfaces.forEach { networkInterface ->
            networkInterface.inetAddresses.toList().forEach { inetAddress ->
                if (!inetAddress.isLoopbackAddress && inetAddress.isSiteLocalAddress) {
                    deviceIPAddress = inetAddress.hostAddress ?: ""
                }
            }
        }
        return deviceIPAddress
    }

    suspend fun getPublicAddress(): String {
        val retrofit = Retrofit.Builder().baseUrl(Constants.PUBLIC_IP_SERVER)
            .build()
        val service = retrofit.create(RetrofitService::class.java)
        val response = service.getUserIP().awaitResponse()
        return response.body()?.string() ?: ""
    }
}


