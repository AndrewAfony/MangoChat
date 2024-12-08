package andrewafony.testapp.data.di

import andrewafony.testapp.data.BuildConfig
import andrewafony.testapp.data.remote.interceptors.AuthInterceptor
import andrewafony.testapp.data.remote.interceptors.MangoAuthenticator
import andrewafony.testapp.data.remote.service.AuthService
import andrewafony.testapp.data.remote.service.MainService
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.Authenticator
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit

internal val networkModule = module {

    single<Interceptor> { AuthInterceptor(get()) }

    single<Authenticator> { MangoAuthenticator(get(), get()) }

    single<HttpLoggingInterceptor> { HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY) }

    single<AuthService> {
        val logging: HttpLoggingInterceptor = get()

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(Json.Default.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

        retrofit.create(AuthService::class.java)
    }

    single<MainService> {
        val logging: HttpLoggingInterceptor = get()
        val authInterceptor: Interceptor = get()
        val authenticator: Authenticator = get()

        val client = OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(authInterceptor)
            .authenticator(authenticator)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_URL)
            .addConverterFactory(Json.Default.asConverterFactory("application/json".toMediaType()))
            .client(client)
            .build()

        retrofit.create(MainService::class.java)
    }
}