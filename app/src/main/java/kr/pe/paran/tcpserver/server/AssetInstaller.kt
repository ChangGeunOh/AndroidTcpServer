package kr.pe.paran.tcpserver.server

import android.content.Context
import android.content.res.AssetManager
import kr.pe.paran.tcpserver.screen.main.MainViewModel
import kr.pe.paran.tcpserver.model.LogData
import java.io.File
import java.io.FileOutputStream

class AssetInstaller(private val viewModel: MainViewModel) {

    fun install(context: Context, sourceDirectory: String = "", targetDirectory: String) {
        recursiveCopy(context.assets, sourceDirectory, targetDirectory)
    }

    private fun recursiveCopy(assetManager: AssetManager, src: String, target: String) {

        val files = assetManager.list(src)

        files?.forEach { filename ->
            val filepath = if (src.isEmpty()) filename else "$src/$filename"
            val targetPath = "$target/$filename"

            if ((assetManager.list(filepath) == null) || (assetManager.list(filepath)?.size == 0)) {
                copyFile(assetManager, filepath, targetPath)
                viewModel.log(LogData(message = "$filepath File Copy..."))
            } else {
                createDirectory(targetPath)
                recursiveCopy(assetManager, filepath, targetPath)
                viewModel.log(LogData(message = "$targetPath Directory Copy..."))
            }
        }
    }

    private fun createDirectory(path: String) {
        val directory = File(path)
        if (!directory.exists()) {
            directory.mkdirs()
        }
    }

    private fun copyFile(assetManager: AssetManager, src: String, target: String) {
        val file = File(target)
        if (file.exists()) file.delete()
        assetManager.open(src).use { input ->
            FileOutputStream(target).use { output -> input.copyTo(output) }
        }
    }
}