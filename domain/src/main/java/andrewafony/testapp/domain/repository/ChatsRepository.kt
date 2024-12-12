package andrewafony.testapp.domain.repository

import andrewafony.testapp.domain.model.Chat
import kotlinx.coroutines.flow.Flow

interface ChatsRepository {

    fun chats() : Flow<List<Chat>>
}