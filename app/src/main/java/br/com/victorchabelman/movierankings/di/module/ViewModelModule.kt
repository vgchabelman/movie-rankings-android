package br.com.victorchabelman.movierankings.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.victorchabelman.movierankings.di.ViewModelFactory
import br.com.victorchabelman.movierankings.di.ViewModelKey
import br.com.victorchabelman.movierankings.viewmodels.MovieDetailViewModel
import br.com.victorchabelman.movierankings.viewmodels.MovieViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(MovieViewModel::class)
    abstract fun bindMovieViewModel(movieViewModel: MovieViewModel): ViewModel

//    @Binds
//    @IntoMap
//    @ViewModelKey(MovieDetailViewModel::class)
//    abstract fun bindMovieDetailViewModel(movieDetailViewModel: MovieDetailViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory) : ViewModelProvider.Factory
}