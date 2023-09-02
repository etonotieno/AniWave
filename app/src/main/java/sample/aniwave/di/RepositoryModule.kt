package sample.aniwave.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import sample.aniwave.data.repository.AnimeRepository
import sample.aniwave.data.repository.DefaultAnimeRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    abstract fun bindAnimeRepository(
        defaultRepository: DefaultAnimeRepository,
    ): AnimeRepository
}
