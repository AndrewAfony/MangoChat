package andrewafony.testapp.data.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import androidx.core.net.toUri
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.File

class ImageHandler(
    private val context: Context
) {

    fun saveImageLocally(newImageUri: Uri): Uri? {
        val file = File(context.filesDir, "profileImage_${System.currentTimeMillis()}")

        if (file.exists()) {
            file.delete()
        }

        return try {
            val inputStream = context.contentResolver.openInputStream(newImageUri)
            val outputStream = file.outputStream()

            inputStream?.use { input ->
                outputStream.use { output ->
                    input.copyTo(output)
                }
            }

            file.toUri()
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }


    fun encode(image: Uri?) : String? {
        if (image == null) return null

        return try {

            val input = context.contentResolver.openInputStream(image)
            val bitmap = BitmapFactory.decodeStream(input , null, null)

            val baos = ByteArrayOutputStream()
            bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            val imageBytes = baos.toByteArray()

            Base64.encodeToString(imageBytes, Base64.DEFAULT)
        } catch (e: Exception) {
            Timber.e(e)
            null
        }
    }

    fun decode(imageString: String) : Bitmap {

        val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return decodedImage
    }
}