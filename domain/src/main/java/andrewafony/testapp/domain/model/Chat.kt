package andrewafony.testapp.domain.model

import java.time.LocalDate
import java.time.LocalTime

data class Chat(
    val id: Int,
    val name: String,
    val image: String,
    val lastMessage: String?,
    val lastMessageTime: LocalTime,
    val lastMessageDate: LocalDate,
    val newMessages: Int
)
