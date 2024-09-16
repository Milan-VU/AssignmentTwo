package assignment.two.network

import assignment.two.models.EntityResponse
import assignment.two.models.LoginRequest
import assignment.two.models.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

// Defines API endpoints for authentication and retrieving dashboard data
interface ApiService {

    // Authenticates the user and returns a keypass
    @POST("{location}/auth")
    suspend fun login(
        @Path("location") location: String,
        @Body request: LoginRequest
    ): LoginResponse

    // Retrieves entities for the dashboard using the keypass
    @GET("dashboard/{keypass}")
    suspend fun getEntities(
        @Path("keypass") keypass: String
    ): EntityResponse
}