package com.example.data

interface OkHttpClientProviderInterface {
    fun getOkHttpClient(): okhttp3.OkHttpClient
    fun cancelAllRequests()
}