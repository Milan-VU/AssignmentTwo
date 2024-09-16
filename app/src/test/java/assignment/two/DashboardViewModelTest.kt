package assignment.two

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import assignment.two.models.Entity
import assignment.two.models.EntityResponse
import assignment.two.network.ApiService
import assignment.two.viewmodels.DashboardViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.junit.Assert.assertEquals

@ExperimentalCoroutinesApi
class DashboardViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var observer: Observer<List<Entity>>

    private lateinit var viewModel: DashboardViewModel
    private val testDispatcher = UnconfinedTestDispatcher()

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)  // Set the main dispatcher to the test dispatcher
        viewModel = DashboardViewModel(apiService)  // Initialize the ViewModel with a mocked ApiService
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()  // Reset the Main dispatcher to its original state
    }

    @Test
    fun `load entities success`() = runTest(testDispatcher) {
        val entityList = listOf(
            Entity(
                artistName = "Radiohead",
                albumTitle = "OK Computer",
                releaseYear = 1997,
                genre = "Alternative Rock",
                trackCount = 12,
                description = "OK Computer is the third studio album by the English alternative rock band Radiohead, released on 16 June 1997. It marks a deliberate attempt by the band to move away from the introspective guitar-oriented sound of their previous album The Bends.",
                popularTrack = "Karma Police"
            )
        )
        val entityResponse = EntityResponse(entities = entityList, entityTotal = 1)

        `when`(apiService.getEntities("test_keypass")).thenReturn(entityResponse)  // Mock API response

        viewModel.entities.observeForever(observer)  // Observe LiveData
        viewModel.loadEntities("test_keypass")  // Trigger loading of entities

        advanceUntilIdle()  // Ensure all coroutines complete

        // Assert that the observer was notified with the correct data
        verify(observer).onChanged(entityList)
        assertEquals(entityList, viewModel.entities.value)
    }
}