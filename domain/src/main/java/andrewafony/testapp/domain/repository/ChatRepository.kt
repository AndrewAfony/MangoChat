package andrewafony.testapp.domain.repository
import andrewafony.testapp.domain.model.ChatMessage
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.coroutines.flow.Flow

interface ChatRepository {

    fun messages() : Flow<SnapshotStateList<ChatMessage>>

    suspend fun newMessage(text: String)

    suspend fun clear()
}