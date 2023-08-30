package sample.aniwave.ui.upload

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import sample.aniwave.ui.theme.AniWaveTheme

@Composable
fun UploadPhotoScreen(modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = "Search by uploading a Screenshot of the anime",
            modifier = Modifier.testTag("search_upload")
        )
    }
}

@Preview
@Composable
private fun UploadPhotoScreenPreview() {
    AniWaveTheme {
        UploadPhotoScreen()
    }
}
