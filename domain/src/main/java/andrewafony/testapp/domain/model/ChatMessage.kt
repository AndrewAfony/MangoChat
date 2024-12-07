package andrewafony.testapp.domain.model

data class ChatMessage(
    val id: Int,
    val message: String,
    val user: String,
    val timestamp: String,
)