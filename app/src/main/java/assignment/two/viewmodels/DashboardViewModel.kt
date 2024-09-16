package assignment.two.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import assignment.two.models.Entity
import assignment.two.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

// ViewModel for managing and loading entities for the Dashboard screen
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val apiService: ApiService
) : ViewModel() {

    // LiveData holding the list of entities to be observed by the UI
    private val _entities = MutableLiveData<List<Entity>>()
    val entities: LiveData<List<Entity>> = _entities

    // Loads entities from the API using the provided keypass
    fun loadEntities(keypass: String) {
        viewModelScope.launch {
            try {
                // Make the API call
                val response = apiService.getEntities(keypass)

                // Process the response as usual
                _entities.value = response.entities

            } catch (e: Exception) {
                // Handle the error appropriately
            }
        }
    }
}