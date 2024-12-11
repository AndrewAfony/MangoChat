package andrewafony.testapp.data.local

import androidx.room.TypeConverter
import java.time.LocalDate

class MangoTypeConverters {

    @TypeConverter
    fun toDate(date: String) : LocalDate = LocalDate.parse(date)

    @TypeConverter
    fun fromDate(date: LocalDate) : String = date.toString()
}