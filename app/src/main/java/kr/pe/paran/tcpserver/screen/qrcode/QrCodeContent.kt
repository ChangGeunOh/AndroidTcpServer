package kr.pe.paran.tcpserver.screen.qrcode

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter

@Composable
fun QrCodeContent(
    qrCode: String?,
    onClickClose: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        qrCode?.let {
            Image(bitmap = getBitMap(it).asImageBitmap(), contentDescription = "QR Code")
        }

        Text(
            text = "스캔하시면 서버와 앱이 연동 됩니다.",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.Gray

        )

        Button(
            modifier = Modifier.padding(top = 36.dp),
            onClick = onClickClose
        ) {
            Text("닫기")
        }
    }
}

private fun getBitMap(qrCode: String): Bitmap {

    val qrCodeWrite = QRCodeWriter()
    val bitMatrix = qrCodeWrite.encode(qrCode, BarcodeFormat.QR_CODE, 700, 700)

    val bitmap = Bitmap.createBitmap(bitMatrix.width, bitMatrix.height, Bitmap.Config.RGB_565)
    for (x in 0 until bitMatrix.width) {
        for (y in 0 until bitMatrix.height) {
            bitmap.setPixel(
                x,
                y,
                if (bitMatrix.get(x, y)) android.graphics.Color.BLACK else android.graphics.Color.WHITE
            )
        }
    }

    return bitmap
}
