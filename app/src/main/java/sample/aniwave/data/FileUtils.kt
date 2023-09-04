package sample.aniwave.data

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.Locale

object FileUtils {

    fun createFile(context: Context): File {
        return File(
            context.cacheDir,
            SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss-SSS",
                Locale.US,
            ).format(System.currentTimeMillis()),
        )
    }

    fun copyStreamToFile(inputStream: InputStream?, outputFile: File) {
        val outputStream = FileOutputStream(outputFile)
        inputStream?.use { input ->
            outputStream.use { output ->
                val buffer = ByteArray(BUFFER_SIZE)
                while (true) {
                    val byteCount = input.read(buffer)
                    if (byteCount < 0) break
                    output.write(buffer, 0, byteCount)
                }
                output.flush()
            }
        }
    }

    private const val BUFFER_SIZE = 4 * 1024
}
