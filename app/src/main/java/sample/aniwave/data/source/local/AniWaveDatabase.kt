package sample.aniwave.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import sample.aniwave.data.source.local.model.LocalAnime

@Database(entities = [LocalAnime::class], version = 1, exportSchema = false)
abstract class AniWaveDatabase : RoomDatabase() {

    abstract fun animeDao(): AnimeDao
}
