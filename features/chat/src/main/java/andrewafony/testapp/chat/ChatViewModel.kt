package andrewafony.testapp.chat

import andrewafony.testapp.domain.repository.ChatRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

class ChatViewModel(
    private val chatRepository: ChatRepository
): ViewModel() {

    val messages = chatRepository.messages()

    fun init() {
        viewModelScope.launch {
            delay(1000)
            addItem()
        }
    }

    fun addItem() {
        viewModelScope.launch {
            chatRepository.newMessage(text = "New message ${Random.nextInt()}")
        }
    }

    fun clear() {
        viewModelScope.launch { chatRepository.clear() }
    }
}


