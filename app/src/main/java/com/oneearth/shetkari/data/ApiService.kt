package com.oneearth.shetkari.data

import retrofit2.http.GET

interface ApiService {
    @GET("resource/9ef84268-d588-465a-a308-a864a43d0070?api-key=579b464db66ec23bdd00000198e1d3339aaa406661c5f2ef0ee53ec4&format=xml")
    suspend fun getData(): ApiResponse
}
