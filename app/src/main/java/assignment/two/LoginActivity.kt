package assignment.two

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import assignment.two.viewmodels.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    // Declare views as class-level properties
    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var errorMessageTextView: TextView
    private var isPasswordVisible = false

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // Initialize views
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        val loginButton = findViewById<Button>(R.id.login)
        errorMessageTextView = findViewById(R.id.error_message)

        // Set up the password visibility toggle
        passwordEditText.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_UP) {
                // Increase the touchable area
                if (event.rawX >= (passwordEditText.right - passwordEditText.compoundDrawables[2].bounds.width() - passwordEditText.paddingRight)) {
                    togglePasswordVisibility()

                    // Call performClick to handle accessibility services
                    passwordEditText.performClick()
                    return@setOnTouchListener true
                }
            }
            false
        }

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (validateInput(username, password)) {
                loginViewModel.login(username, password, LOCATION)
            }
        }

        observeLoginResult()
    }

    // Toggle password visibility
    private fun togglePasswordVisibility() {
        if (isPasswordVisible) {
            passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye_cross, 0)
        } else {
            passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0)
        }
        isPasswordVisible = !isPasswordVisible
        passwordEditText.setSelection(passwordEditText.text.length)
    }

    // Observe the login result from the ViewModel
    private fun observeLoginResult() {
        loginViewModel.loginResult.observe(this) { result ->
            result.fold(
                onSuccess = {
                    navigateToDashboard(it.keypass)
                },
                onFailure = {
                    showError("Invalid username or password")
                }
            )
        }
    }

    // Validate input fields
    private fun validateInput(username: String, password: String): Boolean {
        var isValid = true

        // Reset error messages
        usernameEditText.error = null
        passwordEditText.error = null
        errorMessageTextView.isVisible = false

        // Check if username field is empty
        if (username.isEmpty()) {
            usernameEditText.error = "Username cannot be empty"
            isValid = false
        } else if (!username.matches(Regex("^[a-zA-Z]+$"))) {
            // Check if username contains only letters
            usernameEditText.error = "Username must contain only letters"
            isValid = false
        } else if (username != "Milan") {
            // Check if username matches "Milan"
            usernameEditText.error = "Username must be 'Milan'"
            isValid = false
        }

        // Check if password field is empty
        if (password.isEmpty()) {
            passwordEditText.error = "Password cannot be empty"
            isValid = false
        } else if (password != "s4663796") {
            // Check if password matches "s4663796"
            passwordEditText.error = "Password must be 's4663796'"
            isValid = false
        }

        return isValid
    }

    // Navigate to the DashboardActivity
    private fun navigateToDashboard(keypass: String) {
        val intent = Intent(this, DashboardActivity::class.java).apply {
            putExtra("keypass", keypass)
        }
        startActivity(intent)
        finish()
    }

    // Show an error message
    private fun showError(message: String) {
        errorMessageTextView.text = message
        errorMessageTextView.isVisible = true
    }

    // Class location
    companion object {
        private const val LOCATION = "footscray"
    }
}