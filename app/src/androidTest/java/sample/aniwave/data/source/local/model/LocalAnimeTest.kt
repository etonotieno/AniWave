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
            rating = 0.0
        )

        val anime = localAnime.toExternal()

        val expectedAnime = Anime(
            id = 1,
            imageUrl = "",
            title = "",
            releaseYear = "",
            rating = 0.0
        )
        assertEquals(expectedAnime, anime)
    }
}
