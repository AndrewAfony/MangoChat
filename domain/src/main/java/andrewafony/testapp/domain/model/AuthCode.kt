package andrewafony.testapp.domain.model

data class AuthCode(
    val accessToken: String,
    val refreshToken: String,
    val userId: Int,
    val isUserExists: Boolean,
)
