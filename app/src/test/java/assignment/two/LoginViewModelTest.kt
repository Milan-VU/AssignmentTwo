package assignment.two

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import assignment.two.models.LoginResponse
import assignment.two.network.ApiService
import assignment.two.viewmodels.LoginViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.any
import org.mockito.kotlin.eq

@ExperimentalCoroutinesApi
class LoginViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var observer: Observer<Result<LoginResponse>>

    private lateinit var viewModel: LoginViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)  // Set the Main dispatcher to the test dispatcher
        MockitoAnnotations.openMocks(this)  // Initialize Mockito annotations
        viewModel = LoginViewModel(apiService)  // Initialize the ViewModel with a mocked ApiService
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()  // Reset the Main dispatcher to its original state
    }

    @Test
    fun `login success`() = runTest(testDispatcher) {
        val expectedResponse = LoginResponse(keypass = "test_keypass")
        `when`(apiService.login(eq("footscray"), any())).thenReturn(expectedResponse)  // Mock API response

        viewModel.loginResult.observeForever(observer)  // Observe LiveData
        viewModel.login(username = "Milan", password = "s4663796", location = "footscray")  // Trigger login

        advanceUntilIdle()  // Ensure all coroutines complete

        // Assert that the LiveData was updated with the expected result
        assertEquals(Result.success(expectedResponse), viewModel.loginResult.value)
    }
}