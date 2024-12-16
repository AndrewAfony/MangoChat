package andrewafony.testapp.shared_data.utils

import java.time.LocalDate

fun String?.toLocalDate(): LocalDate? {
    return try {
        LocalDate.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun LocalDate.toZodiac() : String = when {
    isBetween(date(year, 21, 3), date(year, 19, 4))  -> "Овен"
    isBetween(date(year, 20, 4), date(year, 20, 5))  -> "Телец"
    isBetween(date(year, 21, 5), date(year, 21, 6))  -> "Близнецы"
    isBetween(date(year, 22, 6), date(year, 22, 7))  -> "Рак"
    isBetween(date(year, 23, 7), date(year, 22, 8))  -> "Лев"
    isBetween(date(year, 23, 8), date(year, 22, 9))  -> "Дева"
    isBetween(date(year, 23, 9), date(year, 23, 10))  -> "Весы"
    isBetween(date(year, 24, 10), date(year, 22, 11))  -> "Скорпион"
    isBetween(date(year, 23, 11), date(year, 21, 12))  -> "Стрелец"
    isBetween(date(year, 22, 12), date(year, 20, 1))  -> "Козерог"
    isBetween(date(year, 21, 1), date(year, 18, 2))  -> "Водолей"
    isBetween(date(year, 19, 2), date(year, 20, 3))  -> "Рыбы"
    else -> "Рыбы"
}

fun LocalDate.isBetween(first: LocalDate, second: LocalDate): Boolean {
    return isAfter(first) && isBefore(second)
}

fun date(year: Int, day: Int, month: Int): LocalDate = LocalDate.of(year, month, day)