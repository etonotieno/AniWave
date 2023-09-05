package sample.aniwave.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import coil.request.ImageRequest
import sample.aniwave.R
import sample.aniwave.data.model.Anime
import sample.aniwave.ui.theme.AniWaveTheme

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()
    val refreshing by viewModel.isRefreshing.collectAsStateWithLifecycle()
    HomeScreen(state = state, refreshing = refreshing, onRefresh = viewModel::refresh)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeScreen(
    state: HomeFeedUiState,
    refreshing: Boolean,
    onRefresh: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val pullRefreshState = rememberPullRefreshState(refreshing, onRefresh)

    Box(
        modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState),
    ) {
        if (!refreshing) {
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                when (state) {
                    is HomeFeedUiState.Error -> {
                        val message = state.message ?: "An error occurred while fetching top anime"
                        Text(text = message)
                    }

                    is HomeFeedUiState.Success -> {
                        if (state.anime.isEmpty())
                            Text(text = "Top animes were not found")
                        else
                            AnimeItems(anime = state.anime)
                    }

                    HomeFeedUiState.Loading -> {}
                }
            }
        }

        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@Composable
fun AnimeItems(anime: List<Anime>, modifier: Modifier = Modifier) {
    LazyVerticalGrid(
        modifier = modifier.fillMaxSize(),
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        items(anime) { item ->
            AnimeItem(anime = item)
        }
    }
}

@Composable
fun AnimeItem(anime: Anime, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(anime.imageUrl)
                .crossfade(true)
                .build(),
            placeholder = painterResource(R.drawable.ic_image_background),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .fillMaxWidth()
                .height(250.dp),
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = anime.title)

        Spacer(modifier = Modifier.height(8.dp))

        Row {
            Text(text = "${anime.releaseYear}")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(Icons.Default.Star, null, tint = MaterialTheme.colorScheme.primary)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "${anime.score}")
        }
    }
}

@Preview
@Composable
private fun AnimeItemPreview() {
    AniWaveTheme {
        AnimeItem(anime = sampleAnime[0])
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF)
@Composable
private fun AnimeItemsPreview() {
    AniWaveTheme {
        AnimeItems(anime = sampleAnime)
    }
}

@Preview
@Composable
private fun HomeScreenLoadingPreview() {
    AniWaveTheme {
        HomeScreen(state = HomeFeedUiState.Loading, refreshing = true, onRefresh = {})
    }
}

@Preview
@Composable
private fun HomeScreenErrorPreview() {
    AniWaveTheme {
        HomeScreen(state = HomeFeedUiState.Error(), refreshing = false, onRefresh = {})
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    AniWaveTheme {
        HomeScreen(
            state = HomeFeedUiState.Success(anime = sampleAnime),
            refreshing = false,
            onRefresh = {},
        )
    }
}

private val sampleAnime = listOf(
    Anime(
        id = 5114,
        imageUrl = "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/1208\\/94745.webp",
        title = "Fullmetal Alchemist: Brotherhood",
        releaseYear = 2009,
        score = 9.1,
        episode = 1,
        rank = 1,
    ),
    Anime(
        id = 5114,
        imageUrl = "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/1208\\/94745.webp",
        title = "Fullmetal Alchemist: Brotherhood",
        releaseYear = 2009,
        score = 9.1,
        episode = 1,
        rank = 2,
    ),
    Anime(
        id = 5114,
        imageUrl = "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/1208\\/94745.webp",
        title = "Fullmetal Alchemist: Brotherhood",
        releaseYear = 2009,
        score = 9.1,
        episode = 1,
        rank = 3,
    ),
    Anime(
        id = 5114,
        imageUrl = "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/1208\\/94745.webp",
        title = "Fullmetal Alchemist: Brotherhood",
        releaseYear = 2009,
        score = 9.1,
        episode = 1,
        rank = 4,
    ),
    Anime(
        id = 5114,
        imageUrl = "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/1208\\/94745.webp",
        title = "Fullmetal Alchemist: Brotherhood",
        releaseYear = 2009,
        score = 9.1,
        episode = 1,
        rank = 5,
    ),
    Anime(
        id = 5114,
        imageUrl = "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/1208\\/94745.webp",
        title = "Fullmetal Alchemist: Brotherhood",
        releaseYear = 2009,
        score = 9.1,
        episode = 1,
        rank = 6,
    ),
    Anime(
        id = 5114,
        imageUrl = "https:\\/\\/cdn.myanimelist.net\\/images\\/anime\\/1208\\/94745.webp",
        title = "Fullmetal Alchemist: Brotherhood",
        releaseYear = 2009,
        score = 9.1,
        episode = 1,
        rank = 7,
    ),
)
