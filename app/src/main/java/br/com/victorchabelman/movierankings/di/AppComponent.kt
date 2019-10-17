package br.com.victorchabelman.movierankings.di

import br.com.victorchabelman.movierankings.MovieApplication
import br.com.victorchabelman.movierankings.di.module.ActivityBindingModule
import br.com.victorchabelman.movierankings.di.module.MovieModule
import br.com.victorchabelman.movierankings.di.module.NetworkModule
import br.com.victorchabelman.movierankings.di.module.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBindingModule::class,
        MovieModule::class,
        NetworkModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent : AndroidInjector<MovieApplication>