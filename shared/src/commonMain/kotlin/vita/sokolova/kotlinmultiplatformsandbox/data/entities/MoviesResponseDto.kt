package vita.sokolova.kotlinmultiplatformsandbox.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MoviesResponseDto(
    @SerialName("Search")
    val results: List<MovieDto>? = null
)