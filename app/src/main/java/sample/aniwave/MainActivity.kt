package sample.aniwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import sample.aniwave.ui.AniWaveApp
import sample.aniwave.ui.theme.AniWaveTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AniWaveTheme {
                AniWaveApp()
            }
        }
    }
}
