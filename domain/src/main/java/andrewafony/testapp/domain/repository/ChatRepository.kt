package andrewafony.testapp.domain.repository
import andrewafony.testapp.domain.model.ChatMessage
import androidx.compose.runtime.snapshots.SnapshotStateList

interface ChatRepository {

    fun messages() : SnapshotStateList<ChatMessage>

    suspend fun newMessage(text: String)
}