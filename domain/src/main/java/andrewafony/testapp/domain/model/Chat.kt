package andrewafony.testapp.domain.model

data class Chat(
    val id: Int,
    val name: String,
    val image: String,
    val lastMessage: String?,
    val lastMessageTime: String,
    val newMessages: Int
)
