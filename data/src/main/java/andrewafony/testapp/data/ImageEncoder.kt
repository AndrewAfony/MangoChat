package andrewafony.testapp.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Base64
import android.util.Log
import java.io.ByteArrayOutputStream

class ImageEncoder(
    private val context: Context
) {

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
            Log.d("MyHelper", "encode image error: ${e.localizedMessage} ")
            null
        }
    }

    fun decode(imageString: String) : Bitmap {

        val imageBytes = Base64.decode(imageString, Base64.DEFAULT)
        val decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

        return decodedImage
    }
}