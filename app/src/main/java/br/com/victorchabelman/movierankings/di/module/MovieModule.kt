package br.com.victorchabelman.movierankings.di.module

import br.com.victorchabelman.movierankings.remote.TmdbService
import br.com.victorchabelman.movierankings.repository.MovieDataRepository
import br.com.victorchabelman.movierankings.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
abstract class MovieModule {
    @Module
    companion object {
        @JvmStatic
        @Provides
        fun provideMovieService(retrofit: Retrofit) : TmdbService {
            return retrofit.create(TmdbService::class.java)
        }
    }

    @Binds
    abstract fun bindMovieRepository(movieDataRepository: MovieDataRepository) : MovieRepository
}