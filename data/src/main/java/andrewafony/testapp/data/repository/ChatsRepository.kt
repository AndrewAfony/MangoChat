package andrewafony.testapp.data.repository

import andrewafony.testapp.domain.model.Chat
import andrewafony.testapp.domain.repository.ChatsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.time.LocalDate
import java.time.LocalTime
import kotlin.random.Random

private const val TEST_IMAGE =
    "https://i.pinimg.com/736x/e3/e7/11/e3e711f554fe2dc5af0c3314b478b040.jpg"

class ChatsRepositoryImpl : ChatsRepository {

    private val chatsList = (1..LocalDate.now().dayOfMonth).map {
        Chat(
            id = Random.nextInt(),
            name = "Chat name $it",
            image = TEST_IMAGE,
            lastMessage = "Last sent message $it",
            lastMessageTime = LocalTime.of(Random.nextInt(0, 24), Random.nextInt(0, 59)),
            lastMessageDate = LocalDate.of(2024, 12, it),
            newMessages = Random.nextInt(0, 5)
        )
    }

    override fun chats(): Flow<List<Chat>> = flowOf(
        chatsList
            .sortedWith(compareByDescending<Chat> { it.lastMessageDate }.thenByDescending { it.lastMessageTime })
    )

}