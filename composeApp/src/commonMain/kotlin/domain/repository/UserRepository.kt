package domain.repository

import data.UserApi
import domain.models.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepository(private val userApi: UserApi) {

    suspend fun getUsers(): Flow<List<User>> {
        return userApi.getUsers().map { userDto ->
           userDto.data?.map {
                User(
                    id = it.id ?: 0,
                    email = it.email.orEmpty(),
                    fullName = "${it.first_name.orEmpty()} ${it.last_name.orEmpty()}",
                    avatar = it.avatar.orEmpty()
                )
            }.orEmpty()
        }
    }

}