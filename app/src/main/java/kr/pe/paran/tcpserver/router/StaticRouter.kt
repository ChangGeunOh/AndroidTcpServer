package kr.pe.paran.tcpserver.router

import android.util.Log
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import java.io.File

fun Routing.static(
    path: String,
    parentPath: String,
    url: String
) {

    File(path).listFiles()?.forEach { file ->
        if (file.isDirectory) {
            val routeUrl = url + file.absolutePath.substring(parentPath.length) + "/{filename}"
            Log.i(":::::", routeUrl)
            this.get(routeUrl) {
                val fileName = call.parameters["filename"]
                val targetFile = File("${file.absolutePath}/$fileName")
                if (targetFile.exists()) {
                    call.respondFile(targetFile)
                } else {
                    call.respond(status = HttpStatusCode.NotFound, "")
                }

            }
            static(file.absolutePath, parentPath, url)
        }
    }

}