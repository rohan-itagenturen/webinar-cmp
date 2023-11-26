package data.models

import kotlinx.serialization.Serializable

@Serializable
data class UsersDto(
    val page: Int?,
    val per_page: Int?,
    val total: Int?,
    val total_pages: Int?,
    val data: List<UserDto>?
) {

    @Serializable
    data class UserDto(
        val id: Int?,
        val email: String?,
        val first_name: String?,
        val last_name: String?,
        val avatar: String?,
    )
}