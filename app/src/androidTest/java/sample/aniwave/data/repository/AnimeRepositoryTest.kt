package sample.aniwave.data.repository

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class AnimeRepositoryTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    private var testDispatcher = UnconfinedTestDispatcher()
    private var testScope = TestScope(testDispatcher)

    private lateinit var animeRepository: AnimeRepository

    @Test
    fun observeAll_exposesLocalAnime() = runTest {
//        val anime = animeRepository.observeAll().first()
//        assertEquals(localAnime.toExternal(), anime)
    }

    @Test
    fun onAnimeLoaded_localDataIsUpdated() = testScope.runTest {
//        animeRepository.loadAnime()
//        val localAnime = localDataSource.observeAll().first()
//        assertEquals(1, localAnime.size)
    }

    @Test
    fun onRefresh_localIsEqualToNetwork() = testScope.runTest {
//        val networkAnime = listOf<NetworkLAnime>()
//        animeRepository.refresh()
//
//        val localAnime = localDataSource.observeAll().first()
//        assertEquals(networkAnime.toLocal(), localAnime)
    }
}
