package br.com.victorchabelman.movierankings.di.module

import br.com.victorchabelman.movierankings.activities.MainActivity
import br.com.victorchabelman.movierankings.di.scope.ActivityScope
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {
    @ActivityScope
    @ContributesAndroidInjector
    abstract fun contributeMainActivity() : MainActivity
}