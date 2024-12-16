package andrewafony.testapp.data.di

import andrewafony.testapp.data.BuildConfig
import andrewafony.testapp.data.remote.interceptors.AuthInterceptor
import andrewafony.testapp.data.remote.interceptors.MangoAuthenticator
import andrewafony.testapp.data.remote.service.AuthService
import andrewafony.testapp.data.remote.service.UserService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

private const val TIMEOUT_SECONDS: Long = 30

internal val networkModule = module {

    single<Interceptor> { AuthInterceptor(get()) }

    single<Authenticator> { MangoAuthenticator(get(), get()) }

    single<HttpLoggingInterceptor> {
        HttpLoggingInterceptor().setLevel(
            if (BuildConfig.DEBUG)
                HttpLoggingInterceptor.Level.BODY
            else
                HttpLoggingInterceptor.Level.NONE
        )
    }

    single<AuthService> {
        val logging: HttpLoggingInterceptor = get()

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(Json.Default.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

        retrofit.create(AuthService::class.java)
    }

    single<UserService> {
        val logging: HttpLoggingInterceptor = get()
        val authInterceptor: Interceptor = get()
        val authenticator: Authenticator = get()

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .callTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(Json.Default.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

        retrofit.create(UserService::class.java)
    }
}