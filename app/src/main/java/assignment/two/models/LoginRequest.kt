package assignment.two.models

// Represents the request body for user login
data class LoginRequest(
    val username: String,
    val password: String
)