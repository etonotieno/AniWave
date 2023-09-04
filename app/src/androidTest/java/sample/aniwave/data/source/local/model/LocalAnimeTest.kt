package sample.aniwave.data.source.local.model

import org.junit.Assert.assertEquals
import org.junit.Test
import sample.aniwave.data.model.Anime

class LocalAnimeTest {

    @Test
    fun localAnime_canBeMappedTo_externalAnime() {
        val localAnime = LocalAnime(
            id = 1,
            imageUrl = "",
            title = "",
            releaseYear = "",
            score = 0.0,
            episode = 1,
            rank = 1,
        )

        val anime = localAnime.toExternal()

        val expectedAnime = Anime(
            id = 1,
            imageUrl = "",
            title = "",
            releaseYear = "",
            score = 0.0,
            episode = 1,
            rank = 2,
        )
        assertEquals(expectedAnime, anime)
    }
}
