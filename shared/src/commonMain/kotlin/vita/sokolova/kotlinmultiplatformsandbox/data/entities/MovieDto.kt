package vita.sokolova.kotlinmultiplatformsandbox.data.entities

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class MovieDto(
    @SerialName("imdbID")
    val id: String,
    @SerialName("Title")
    val title: String,
    @SerialName("Poster")
    val poster: String? = null,
    @SerialName("Year")
    val year: String
)