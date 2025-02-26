package com.example.data

import okhttp3.OkHttpClient

class OkHttpClientProvider: OkHttpClientProviderInterface {
    override fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }

    override fun cancelAllRequests() {
        // Cancel all requests
    }
}