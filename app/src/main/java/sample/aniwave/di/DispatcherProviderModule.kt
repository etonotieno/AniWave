package sample.aniwave.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sample.aniwave.data.coroutines.DefaultDispatcherProvider
import sample.aniwave.data.coroutines.DispatcherProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DispatcherProviderModule {

    @Singleton
    @Binds
    abstract fun bindDispatcherProvider(
        defaultProvider: DefaultDispatcherProvider,
    ): DispatcherProvider
}
