import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.withOptions
import org.koin.dsl.module
import vita.sokolova.kotlinmultiplatformsandbox.data.HttpClientHolder
import vita.sokolova.kotlinmultiplatformsandbox.data.MoviesRepositoryImpl
import vita.sokolova.kotlinmultiplatformsandbox.domain.repositories.MoviesRepository

// Provide the dependencies through a Koin module
val sharedModule = module {
    single { MoviesRepositoryImpl(HttpClientHolder.client) } withOptions {
        bind<MoviesRepository>()
    }
}