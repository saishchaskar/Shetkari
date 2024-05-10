package com.oneearth.shetkari.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.StringReader
import javax.xml.bind.JAXBContext

class DataRepository {

    private val client = OkHttpClient()

    suspend fun fetchData(apiUrl: String): ApiResponse {
        return withContext(Dispatchers.IO) {
            val request = Request.Builder()
                .url(apiUrl)
                .get()
                .build()

            val response = client.newCall(request).execute()
            val responseBody = response.body?.string().orEmpty()

            // Parse XML using JAXB
            val jaxbContext = JAXBContext.newInstance(ApiResponse::class.java)
            val unmarshaller = jaxbContext.createUnmarshaller()
            val apiResponse = unmarshaller.unmarshal(StringReader(responseBody)) as ApiResponse

            return@withContext apiResponse
        }
    }
}
