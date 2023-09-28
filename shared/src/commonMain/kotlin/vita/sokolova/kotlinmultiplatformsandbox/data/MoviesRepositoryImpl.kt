package vita.sokolova.kotlinmultiplatformsandbox.data

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.request
import io.ktor.http.HttpMethod
import vita.sokolova.kotlinmultiplatformsandbox.data.entities.MoviesResponseDto
import vita.sokolova.kotlinmultiplatformsandbox.domain.entities.Movie
import vita.sokolova.kotlinmultiplatformsandbox.domain.repositories.MoviesRepository

class MoviesRepositoryImpl (val httpClient: HttpClient) : MoviesRepository {

    @Throws(Exception::class)
    override suspend fun getSearchResults(query: String): List<Movie> {
        return if (query.isNotEmpty()) {
            val response: MoviesResponseDto = httpClient.request("https://www.omdbapi.com/") {
                method = HttpMethod.Get
                url {
                    parameters.append("s", query)
                    parameters.append("apikey", "1ef1c5d3")
                }
            }.body()
            response.results?.map {
                Movie(it.id, it.title, it.poster, it.year)
            } ?: emptyList()
        } else {
            emptyList()
        }
    }
}