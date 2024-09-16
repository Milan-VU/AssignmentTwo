package assignment.two.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import assignment.two.models.LoginRequest
import assignment.two.models.LoginResponse
import assignment.two.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing user login operations
@HiltViewModel
class LoginViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    // LiveData holding the result of the login attempt
    private val _loginResult = MutableLiveData<Result<LoginResponse>>()
    val loginResult: LiveData<Result<LoginResponse>> = _loginResult

    // Attempts to log in the user with the provided credentials
    fun login(username: String, password: String, location: String) {
        viewModelScope.launch {
            try {
                val request = LoginRequest(username, password)
                val response = apiService.login(location, request)
                _loginResult.value = Result.success(response)
            } catch (e: Exception) {
                _loginResult.value = Result.failure(e)
            }
        }
    }
}