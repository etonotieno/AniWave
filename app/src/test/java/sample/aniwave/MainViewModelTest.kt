package sample.aniwave

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class MainViewModelTest {

    // Subject
    private lateinit var viewModel: MainViewModel

    @Before
    fun setUp() {
        viewModel = MainViewModel()
    }

    @Test
    fun initialValue_isCorrect() {
        assertEquals("Android", viewModel.uiState.value)
    }
}
