package com.example.data

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Locale

const val AUTHORIZATION_HEADER = "Authorization"
const val CONTENT_TYPE = "Content-Type"
const val ACCEPT_LANGUAGE = "Accept-Language"
const val CLIENT_ID = "Client-Id"
const val ACCEPT_HEADER = "Accept"

const val APPLICATION_JSON = "application/json"
const val ENGLISH_LANGUAGE = "en"

class HeaderInterceptor(
    private val clientId: String,
    private val accessTokenProvider: () -> String?,
    private val languageProvider: () -> Locale,
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val builder = request.newBuilder()
        builder
            .header(CLIENT_ID, clientId)
            .header(ACCEPT_HEADER, APPLICATION_JSON)
            .header(CONTENT_TYPE, APPLICATION_JSON)
            .header(ACCEPT_LANGUAGE, languageProvider().language)

        accessTokenProvider()?.let {
            builder.header(AUTHORIZATION_HEADER, "Bearer $it")
        }

        return chain.proceed(builder.build())
    }
}
