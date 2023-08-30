package sample.aniwave.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import sample.aniwave.ui.theme.AniWaveTheme

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AniWaveTheme {
        HomeScreen()
    }
}
