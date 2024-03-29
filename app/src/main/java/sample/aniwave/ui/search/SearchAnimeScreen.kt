/*
 * Copyright 2023 Eton Otieno
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.aniwave.ui.search

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import sample.aniwave.R
import sample.aniwave.data.FileUtils
import sample.aniwave.ui.theme.AniWaveTheme

@Composable
fun SearchAnimeScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchAnimeViewModel = hiltViewModel(),
) {
    val context = LocalContext.current

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    SearchAnimeScreen(
        modifier = modifier,
        state = uiState,
        onSearch = { uri ->
            val file = FileUtils.createFile(context)
            viewModel.searchAnime(
                writeToFile = {
                    FileUtils.copyStreamToFile(context.contentResolver.openInputStream(uri), file)
                },
                file = file,
            )
        },
    )
}

@Composable
fun SearchAnimeScreen(
    onSearch: (Uri) -> Unit,
    state: SearchUiState,
    modifier: Modifier = Modifier,
) {
    var photoUri: Uri? by remember { mutableStateOf(null) }
    val launcher =
        rememberLauncherForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            photoUri = uri
            if (uri != null) {
                onSearch(uri)
            }
        }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.height(16.dp))

        when (state) {
            is SearchUiState.Error -> {
                val message = state.message ?: "An error occurred while searching the anime"
                Text(text = message)
            }

            SearchUiState.Initial -> {
                SearchPlaceholder()
            }

            SearchUiState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    trackColor = MaterialTheme.colorScheme.secondary,
                )
            }

            is SearchUiState.Success -> {
                val asyncPainter = rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(state.anime.imageUrl)
                        .placeholder(R.drawable.ic_image_background)
                        .crossfade(true)
                        .build(),
                )

                Image(
                    painter = asyncPainter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .clip(RoundedCornerShape(16.dp))
                        .fillMaxWidth()
                        .height(250.dp),
                )

                Spacer(Modifier.height(16.dp))

                Text(text = state.anime.title)

                Spacer(Modifier.height(16.dp))

                Text(text = "Episode: ${state.anime.episode}")
            }
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = {
                launcher.launch(
                    PickVisualMediaRequest(
                        mediaType = ActivityResultContracts.PickVisualMedia.ImageOnly,
                    ),
                )
            },
        ) {
            Text(text = "Pick Photo")
        }
    }
}

@Preview
@Composable
private fun SearchPhotoScreenLoadingPreview() {
    AniWaveTheme {
        SearchAnimeScreen(onSearch = {}, state = SearchUiState.Loading)
    }
}
