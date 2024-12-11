package andrewafony.testapp.data.local

import android.net.Uri
import androidx.room.TypeConverter
import java.time.LocalDate

class MangoTypeConverters {

    // Date

    @TypeConverter
    fun toDate(date: String) : LocalDate = LocalDate.parse(date)

    @TypeConverter
    fun fromDate(date: LocalDate) : String = date.toString()

    // Image

    @TypeConverter
    fun toUri(image: String) : Uri = Uri.parse(image)

    @TypeConverter
    fun fromUri(image: Uri): String = image.toString()
}