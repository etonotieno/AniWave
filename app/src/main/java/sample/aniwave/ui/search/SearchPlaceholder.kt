package sample.aniwave.ui.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import sample.aniwave.ui.theme.AniWaveTheme

@Composable
fun SearchPlaceholder(modifier: Modifier = Modifier) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = "Search for Anime by uploading a Screenshot of the anime",
            modifier = Modifier
                .testTag("search_upload"),
            textAlign = TextAlign.Center,
        )
    }
}

@Preview
@Composable
private fun SearchPlaceholderPreview() {
    AniWaveTheme {
        SearchPlaceholder()
    }
}
