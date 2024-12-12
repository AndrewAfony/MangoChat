package andrewafony.testapp.data.repository

import andrewafony.testapp.domain.model.Chat
import andrewafony.testapp.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlin.random.Random

private const val TEST_IMAGE =
    "https://i.pinimg.com/736x/e3/e7/11/e3e711f554fe2dc5af0c3314b478b040.jpg"

class ChatsRepositoryImpl : ChatsRepository {

    private val chatsList = (1..15).map {
        Chat(
            id = it,
            name = "Chat name $it",
            image = TEST_IMAGE,
            lastMessage = "Last sent message $it",
            lastMessageTime = "${Random.nextInt(12, 20)}:${Random.nextInt(10, 59)}",
            newMessages = Random.nextInt(0, 5)
        )
    }

    override fun chats(): Flow<List<Chat>> = flowOf(chatsList)
}