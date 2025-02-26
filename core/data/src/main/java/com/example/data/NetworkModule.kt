package com.example.data

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Call
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("Language")
    fun providerLanguage(): () -> Locale {
        return { Locale.ENGLISH }
    }

    @Provides
    @Singleton
    @Named("AccessToken")
    fun providerAccessToken(): () -> String? {
        return { null }
    }

    @Provides
    @Singleton
    @Named("ClientId")
    fun providerClientId(): String {
        return "null"
    }

    @Provides
    @Singleton
    @Named("HeaderInterceptor")
    fun provideHeaderInterceptor(
        @Named("ClientId") clientId: String,
        @Named("AccessToken") accessToken: () -> String?,
        @Named("Language") languageProvider:() -> Locale
    ): Interceptor = HeaderInterceptor(clientId, accessToken, languageProvider)

    @Provides
    @Singleton
    @Named("LoggingInterceptor")
    fun provideLoggingInterceptor(): Interceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level =
            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }

        if (!BuildConfig.DEBUG) {
            interceptor.redactQueryParams(CLIENT_ID)
            interceptor.redactQueryParams(AUTHORIZATION_HEADER)
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpCallFactory(interceptor: Interceptor): Call.Factory {
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .retryOnConnectionFailure(true)
            .followRedirects(false)
            .followSslRedirects(false)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }
}
