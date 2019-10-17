package br.com.victorchabelman.movierankings.di.module

import br.com.victorchabelman.movierankings.remote.TmdbService
import br.com.victorchabelman.movierankings.repository.MovieDataRepository
import br.com.victorchabelman.movierankings.repository.MovieRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
abstract class MovieModule {
    @Module
    companion object {
        @JvmStatic
        @Singleton
        @Provides
        fun provideMovieService(retrofit: Retrofit) : TmdbService {
            return retrofit.create(TmdbService::class.java)
        }
    }

    @Binds
    abstract fun bindMovieRepository(movieDataRepository: MovieDataRepository) : MovieRepository
}