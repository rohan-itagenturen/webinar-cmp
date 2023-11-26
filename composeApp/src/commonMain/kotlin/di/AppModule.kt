package di

import data.UserApi
import domain.repository.UserRepository

object AppModule {

    fun getUserRepository() : UserRepository {
        return UserRepository(UserApi)
    }
}