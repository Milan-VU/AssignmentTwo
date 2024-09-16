package assignment.two.di

import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import assignment.two.network.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// Provides network-related dependencies like Retrofit and ApiService
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    // VU API
    private const val BASE_URL = "https://vu-nit3213-api.onrender.com/"

    // Provides singleton instance configured with the base URL & Moshi
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    // Provides singleton instance of ApiService using provided Retrofit instance
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}