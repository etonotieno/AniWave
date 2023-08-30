package sample.aniwave

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import sample.aniwave.ui.theme.AniWaveTheme

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
