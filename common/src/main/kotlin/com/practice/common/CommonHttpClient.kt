package com.practice.common

import org.springframework.stereotype.Component
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpRequest.BodyPublishers
import java.net.http.HttpResponse

@Component
class CommonHttpClient(
    private val httpClient: HttpClient = HttpClient.newBuilder().build()
) {

    fun sendGetRequest(url: String): HttpResponse<String> {
        val httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .GET()
            .build()
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    }

    fun sendPostRequest(url: String, jsonBody: String): HttpResponse<String> {
        val httpRequest = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .POST(BodyPublishers.ofString(jsonBody))
            .build()
        return httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString())
    }
}