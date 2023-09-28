package vita.sokolova.kotlinmultiplatformsandbox.domain.repositories

import vita.sokolova.kotlinmultiplatformsandbox.domain.entities.Movie

interface MoviesRepository {

    @Throws(Exception::class)
    suspend fun getSearchResults(query: String): List<Movie>
}