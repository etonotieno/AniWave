package sample.aniwave.di

import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import sample.aniwave.data.repository.AnimeRepository
import sample.aniwave.data.repository.TestAnimeRepository
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class],
)
abstract class TestRepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAnimeRepository(
        defaultRepository: TestAnimeRepository,
    ): AnimeRepository
}
