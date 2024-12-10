package andrewafony.testapp.data.remote.model.request

import kotlinx.serialization.Serializable

@Serializable
data class Auth(val phone: String)