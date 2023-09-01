package sample.aniwave.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import sample.aniwave.data.coroutines.DispatcherProvider
import sample.aniwave.data.coroutines.TestDispatcherProvider
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DispatcherProviderModule::class],
)
abstract class TestDispatcherProviderModule {

    @Singleton
    @Binds
    abstract fun bindDispatcherProvider(
        defaultProvider: TestDispatcherProvider,
    ): DispatcherProvider
}
