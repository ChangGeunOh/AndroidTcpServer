package kr.pe.paran.tcpserver.model

import android.os.Parcel
import android.os.Parcelable

data class IpAddressData(
    val publicAddress: String = "",
    val deviceAddress: String = ""
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!
    ) {}

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(publicAddress)
        parcel.writeString(deviceAddress)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<IpAddressData> {
        override fun createFromParcel(parcel: Parcel): IpAddressData {
            return IpAddressData(parcel)
        }

        override fun newArray(size: Int): Array<IpAddressData?> {
            return arrayOfNulls(size)
        }
    }
}
