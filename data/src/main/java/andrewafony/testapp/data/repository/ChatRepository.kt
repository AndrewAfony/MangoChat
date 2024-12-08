package andrewafony.testapp.data.repository

import andrewafony.testapp.domain.model.ChatMessage
import andrewafony.testapp.domain.repository.ChatRepository
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.random.Random

class ChatRepositoryImpl: ChatRepository {

    private val messages = mutableStateListOf<ChatMessage>()
    private var uniqueId = 3

    override fun messages(): Flow<SnapshotStateList<ChatMessage>> = flow {
        messages.clear()
        delay(Random.nextLong(500, 1500))
        emit(messages)
    }

    override suspend fun newMessage(text: String) {
        messages.add(
            0,
            ChatMessage(
                id = ++uniqueId,
                message = text,
                user = "user",
                timestamp = System.currentTimeMillis().toString()
            )
        )
    }

    override suspend fun clear() {
        messages.clear()
    }
}