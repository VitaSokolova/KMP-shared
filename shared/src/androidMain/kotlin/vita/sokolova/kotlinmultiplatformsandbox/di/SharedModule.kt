package vita.sokolova.kotlinmultiplatformsandbox.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.java.KoinJavaComponent.get
import sharedModule
import vita.sokolova.kotlinmultiplatformsandbox.domain.repositories.MoviesRepository

@Module
@InstallIn(SingletonComponent::class)
class SharedModule {

    // #2: Provide the SharedPrinter into the Hilt graph..
    @Provides
    fun provideMoviesRepository(): MoviesRepository {
        // #3: .. by actually retrieving it from the shared Koin module.
        return get(MoviesRepository::class.java)
    }

    companion object {
        fun init(context: Context) {
            // #1: Instantiate Koin first.
            startKoin {
                androidContext(context)
                modules(sharedModule)
            }
        }
    }
}