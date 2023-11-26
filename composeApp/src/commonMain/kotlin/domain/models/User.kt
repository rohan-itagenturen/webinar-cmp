package domain.models

data class User(
    val id: Int,
    val email: String,
    val fullName: String,
    val avatar: String,
)