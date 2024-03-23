package com.fraporitmos.yapechallengue.dataAccess

import com.fraporitmos.yapechallengue.core.entities.Recipe
import com.google.gson.Gson
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import java.net.HttpURLConnection

@RunWith(MockitoJUnitRunner::class)
class ResponseServerTest {
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
    }
    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `Json file is NotNull`() {
        val json = JSONFileLoader().loadJSONString("recipes_response_success.json")
        assertThat(json, `is`(notNullValue()))
    }

    @Test
    fun `Client response is NotNull`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JSONFileLoader().loadJSONString("recipes_response_success.json") ?: "")
        mockWebServer.enqueue(response)
        assertThat(response.getBody()?.readUtf8(), `is`(notNullValue()))
    }

    @Test
    fun `Recipes list entities is not empty`() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(JSONFileLoader().loadJSONString("recipes_response_success.json") ?: "")
        mockWebServer.enqueue(response)
        val json = Gson().fromJson(response.getBody()?.readUtf8(), Array<Recipe>::class.java).toList()
        assertThat(json.isEmpty(), `is`(false))
    }

}