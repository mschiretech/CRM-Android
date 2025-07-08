import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mschiretech.crm_android.core.model.User
import com.mschiretech.crm_android.core.network.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _postResult = MutableStateFlow("")
    val postResult: StateFlow<String> = _postResult

    private val _foundUser = MutableStateFlow<User?>(null)
    val foundUser: StateFlow<User?> = _foundUser

    private val _searchResult = MutableStateFlow("")
    val searchResult: StateFlow<String> = _searchResult

    fun createUser(name: String, username: String, email: String, password: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                val newUser =
                    User(name = name, username = username, email = email, password = password)
                val response = RetrofitClient.api.createUser(newUser)
                _postResult.value = "User Created: ${response.name} (${response.id})"
            } catch (e: Exception) {
                _postResult.value = "Post Error: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun searchUserByEmail(email: String) {
        viewModelScope.launch {
            try {
                val result = RetrofitClient.api.searchUserByEmail(email)
                _searchResult.value = if (result.isNotEmpty()) {
                    val user = result.first()
                    "Found: ${user.name} (${user.email})"
                } else {
                    "No user found with email: $email"
                }
            } catch (e: Exception) {
                _searchResult.value = "Error: ${e.message}"
            }
        }
    }

    fun clearSearchResults() {
        _foundUser.value = null
        _searchResult.value = ""
    }

    fun resetState() {
        _postResult.value = ""
        _searchResult.value = ""
        _foundUser.value = null
    }
}