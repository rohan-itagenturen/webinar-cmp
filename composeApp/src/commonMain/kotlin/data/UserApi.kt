package data

import data.models.UsersDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.serialization.json.Json

object UserApi {

    private val BASE_URL = "https://reqres.in"
    private val getUserUrl = "$BASE_URL/api/users"

    private val client = HttpClient() {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
    }

    suspend fun getUsers(): Flow<UsersDto> {
        return channelFlow {
            delay(1000L)
            send(client.get(getUserUrl).body())
        }
    }
}